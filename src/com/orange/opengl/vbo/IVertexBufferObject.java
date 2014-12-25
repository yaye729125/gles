package com.orange.opengl.vbo;

import java.nio.ByteBuffer;

import com.orange.opengl.shader.ShaderProgram;
import com.orange.opengl.util.GLState;
import com.orange.util.IDisposable;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IVertexBufferObject extends IDisposable {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int HARDWARE_BUFFER_ID_INVALID = -1;

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isAutoDispose();

	public int getHardwareBufferID();

	public boolean isLoadedToHardware();
	/**
	 * Mark this {@link VertexBufferObject} as not not loaded to hardware.
	 * It will reload itself to hardware when it gets used again.
	 */
	public void setNotLoadedToHardware();
	public void unloadFromHardware(final GLState pGLState);

	public boolean isDirtyOnHardware();
	/** Mark this {@link VertexBufferObject} dirty so it gets updated on the hardware. */
	public void setDirtyOnHardware();

	/**
	 * @return the number of <code>float</code>s that fit into this {@link IVertexBufferObject}.
	 */
	public int getCapacity();
	/**
	 * @return the number of <code>byte</code>s that fit into this {@link IVertexBufferObject}.
	 */
	public int getByteCapacity();

	/**
	 * @return the number of <code>byte</code>s that are allocated on the heap.
	 */
	public int getHeapMemoryByteSize();
	/**
	 * @return the number of <code>byte</code>s that are allocated on the native heap (through direct {@link ByteBuffer}s).
	 */
	public int getNativeHeapMemoryByteSize();
	/**
	 * @return the number of <code>byte</code>s that are allocated on the GPU.
	 */
	public int getGPUMemoryByteSize();

	public void bind(final GLState pGLState);
	public void bind(final GLState pGLState, final ShaderProgram pShaderProgram);
	public void unbind(final GLState pGLState, final ShaderProgram pShaderProgram);

	public VertexBufferObjectManager getVertexBufferObjectManager();

	public void draw(final int pPrimitiveType, final int pCount);
	public void draw(final int pPrimitiveType, final int pOffset, final int pCount);
}
