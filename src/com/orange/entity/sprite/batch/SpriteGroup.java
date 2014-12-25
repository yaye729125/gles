package com.orange.entity.sprite.batch;

import java.util.ArrayList;

import com.orange.entity.IEntity;
import com.orange.entity.sprite.Sprite;
import com.orange.entity.sprite.batch.vbo.ISpriteBatchVertexBufferObject;
import com.orange.opengl.shader.ShaderProgram;
import com.orange.opengl.texture.ITexture;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.util.adt.list.SmartList;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
@Deprecated
public abstract class SpriteGroup extends DynamicSpriteBatch {

	// ===========================================================
	// 此类尚未完成，暂时删除，抽象修饰和构造方法是临时加进来的。
	// ===========================================================
	
	public SpriteGroup(float pX, float pY, ITexture pTexture, int pCapacity, ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
		super(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject);
		// TODO Auto-generated constructor stub
	}


//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager) {
//		super(pTexture, pCapacity, pVertexBufferObjectManager);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager) {
//		super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
//		super(pTexture, pCapacity, pVertexBufferObjectManager, pDrawType);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
//		super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pDrawType);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
//		super(pTexture, pCapacity, pVertexBufferObjectManager, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
//		super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
//		super(pTexture, pCapacity, pVertexBufferObjectManager, pDrawType, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
//		super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pDrawType, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
//		super(pTexture, pCapacity, pSpriteBatchVertexBufferObject);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
//		super(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, final ShaderProgram pShaderProgram) {
//		super(pTexture, pCapacity, pSpriteBatchVertexBufferObject, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	public SpriteGroup(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, final ShaderProgram pShaderProgram) {
//		super(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject, pShaderProgram);
//
//		/* Make children not be drawn automatically, as we handle the drawing ourself. */
//		this.setChildrenVisible(false);
//	}
//
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//
//	/**
//	 * Instead use {@link SpriteGroup#attachChild(BaseSprite)}.
//	 */
//	@Override
//	@Deprecated
//	public void attachChild(final IEntity pEntity) throws IllegalArgumentException {
//		if(pEntity instanceof Sprite) {
//			this.attachChild((Sprite)pEntity);
//		} else {
//			throw new IllegalArgumentException("A " + SpriteGroup.class.getSimpleName() + " can only handle children of type Sprite or subclasses of Sprite, like TiledSprite or AnimatedSprite.");
//		}
//	}
//
//	public void attachChild(final Sprite pSprite) {
//		this.assertCapacity();
//		this.assertTexture(pSprite.getTextureRegion());
//		super.attachChild(pSprite);
//	}
//
//	public void attachChildren(final ArrayList<? extends Sprite> pSprites) {
//		final int baseSpriteCount = pSprites.size();
//		for(int i = 0; i < baseSpriteCount; i++) {
//			this.attachChild(pSprites.get(i));
//		}
//	}
//
//	@Override
//	protected boolean onUpdateSpriteBatch() {
//		final SmartList<IEntity> children = this.mChildren;
//		if(children == null) {
//			return false;
//		} else {
//			final int childCount = children.size();
//			for(int i = 0; i < childCount; i++) {
//				this.drawWithoutChecks((Sprite)children.get(i));
//			}
//			return true;
//		}
//	}
//
//	// ===========================================================
//	// Methods
//	// ===========================================================
//
//	private void assertCapacity() {
//		if(this.getChildCount() >= this.mCapacity) {
//			throw new IllegalStateException("This " + SpriteGroup.class.getSimpleName() + " has already reached its capacity (" + this.mCapacity + ") !");
//		}
//	}
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================

}
