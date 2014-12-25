package com.orange.opengl.vbo.attribute;

import com.orange.util.adt.DataConstants;
import com.orange.util.exception.AndEngineRuntimeException;
import com.orange.util.system.SystemUtils;

import android.opengl.GLES20;
import android.os.Build;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class VertexBufferObjectAttributesBuilder {
	// ===========================================================
	// Constants
	// ===========================================================

	/** Android issue 8931. */
	private static final boolean WORAROUND_GLES2_GLVERTEXATTRIBPOINTER_MISSING;

	static {
		WORAROUND_GLES2_GLVERTEXATTRIBPOINTER_MISSING = SystemUtils.isAndroidVersionOrLower(Build.VERSION_CODES.FROYO);
	}

	// ===========================================================
	// Fields
	// ===========================================================

	private int mIndex;
	private final VertexBufferObjectAttribute[] mVertexBufferObjectAttributes;

	private int mOffset;

	// ===========================================================
	// Constructors
	// ===========================================================

	public VertexBufferObjectAttributesBuilder(final int pCount) {
		this.mVertexBufferObjectAttributes = new VertexBufferObjectAttribute[pCount];
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public VertexBufferObjectAttributesBuilder add(final int pLocation, final String pName, final int pSize, final int pType, final boolean pNormalized) {
		if(VertexBufferObjectAttributesBuilder.WORAROUND_GLES2_GLVERTEXATTRIBPOINTER_MISSING) {
			this.mVertexBufferObjectAttributes[this.mIndex] = new VertexBufferObjectAttributeFix(pLocation, pName, pSize, pType, pNormalized, this.mOffset);
		} else {
			this.mVertexBufferObjectAttributes[this.mIndex] = new VertexBufferObjectAttribute(pLocation, pName, pSize, pType, pNormalized, this.mOffset);
		}

		switch(pType) {
			case GLES20.GL_FLOAT:
				this.mOffset += pSize * DataConstants.BYTES_PER_FLOAT;
				break;
			case GLES20.GL_UNSIGNED_BYTE:
				this.mOffset += pSize * DataConstants.BYTES_PER_BYTE;
				break;
			default:
				throw new IllegalArgumentException("Unexpected pType: '" + pType + "'.");
		}

		this.mIndex++;

		return this;
	}

	public VertexBufferObjectAttributes build() {
		if(this.mIndex != this.mVertexBufferObjectAttributes.length) {
			throw new AndEngineRuntimeException("Not enough " + VertexBufferObjectAttribute.class.getSimpleName() + "s added to this " + this.getClass().getSimpleName() + ".");
		}

		return new VertexBufferObjectAttributes(this.mOffset, this.mVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}