package com.orange.entity.group;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import android.util.SparseArray;
import android.view.View;

import com.orange.engine.camera.Camera;
import com.orange.entity.Entity;
import com.orange.entity.IEntity;
import com.orange.entity.IEntityComparator;
import com.orange.entity.IEntityMatcher;
import com.orange.entity.IEntityParameterCallable;
import com.orange.entity.ZIndexSorter;
import com.orange.entity.layout.EntityLayout;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.util.Constants;
import com.orange.util.adt.list.SmartList;
import com.orange.util.call.ParameterCallable;
import com.orange.util.debug.Debug;
import com.orange.util.size.Size;

@SuppressLint("WrongCall")
public class BaseEntityGroup extends Entity implements IEntityGroup {

	// ===========================================================
	// 常量
	// ===========================================================

	private static final int CHILDREN_CAPACITY_DEFAULT = 4;

	private static final ParameterCallable<IEntity> PARAMETERCALLABLE_DETACHCHILD = new ParameterCallable<IEntity>() {
		@Override
		public void call(final IEntity pEntity) {
			pEntity.setParent(null);
			pEntity.onDetached();
		}
	};

	// ===========================================================
	// 变量
	// ===========================================================

	private boolean mClippingEnabled = false;
	private boolean mChildrenVisible = true;
	private boolean mChildrenIgnoreUpdate;
	private boolean mChildrenSortPending;

	private SmartList<IEntity> mChildren;
	private final SparseArray<IEntity> mTouchAreaBindings = new SparseArray<IEntity>();

	// ===========================================================
	// 构造
	// ===========================================================

	/**
	 * 默认位置(0,0)，宽高无穷大
	 */
	public BaseEntityGroup() {
		this(0, 0, Size.SIZE_INFINITE, Size.SIZE_INFINITE);
	}

	/**
	 * 
	 * @param pWidth
	 *            {@link Entity#SIZE_INFINITE} -1为无穷大
	 * @param pHeight
	 *            {@link Entity#SIZE_INFINITE} -1为无穷大
	 */
	public BaseEntityGroup(float pWidth, float pHeight) {
		this(0, 0, pWidth, pHeight);
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pWidth
	 *            {@link Entity#SIZE_INFINITE} -1为无穷大
	 * @param pHeight
	 *            {@link Entity#SIZE_INFINITE} -1为无穷大
	 */
	public BaseEntityGroup(float pX, float pY, float pWidth, float pHeight) {
		super(pX, pY, pWidth, pHeight);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// child操作
	// ===========================================================

	@Override
	public boolean isChildrenVisible() {
		return this.mChildrenVisible;
	}

	@Override
	public void setChildrenVisible(final boolean pChildrenVisible) {
		this.mChildrenVisible = pChildrenVisible;
	}

	@Override
	public boolean isChildrenIgnoreUpdate() {
		return this.mChildrenIgnoreUpdate;
	}

	@Override
	public void setChildrenIgnoreUpdate(final boolean pChildrenIgnoreUpdate) {
		this.mChildrenIgnoreUpdate = pChildrenIgnoreUpdate;
	}

	@Override
	public SmartList<IEntity> getChildren() {
		return this.mChildren;
	}

	@Override
	public int getChildCount() {
		if (this.mChildren == null) {
			return 0;
		}
		return this.mChildren.size();
	}

	@Override
	public IEntity getChildByTag(final int pTag) {
		if (this.mChildren == null) {
			return null;
		}
		for (int i = this.mChildren.size() - 1; i >= 0; i--) {
			final IEntity child = this.mChildren.get(i);
			if (child.getTag() == pTag) {
				return child;
			}
		}
		return null;
	}

	@Override
	public IEntity getChildByIndex(final int pIndex) {
		if (this.mChildren == null) {
			return null;
		}
		return this.mChildren.get(pIndex);
	}

	@Override
	public IEntity getChildByMatcher(final IEntityMatcher pEntityMatcher) {
		if (this.mChildren == null) {
			return null;
		}
		return this.mChildren.get(pEntityMatcher);
	}

	@Override
	public IEntity getFirstChild() {
		if (this.mChildren == null) {
			return null;
		}
		return this.mChildren.get(0);
	}

	@Override
	public IEntity getLastChild() {
		if (this.mChildren == null) {
			return null;
		}
		return this.mChildren.get(this.mChildren.size() - 1);
	}

	@Override
	public void detachChildren() {
		if (this.mChildren == null) {
			return;
		}
		this.mChildren.clear(BaseEntityGroup.PARAMETERCALLABLE_DETACHCHILD);
	}

	@Override
	public void attachChild(final IEntity pEntity) throws IllegalStateException {
		this.attachChild(pEntity, -1);
	}

	@Override
	public void attachChild(IEntity pEntity, int pIndex) throws IllegalStateException {
		// TODO Auto-generated method stub
		this.assertEntityHasNoParent(pEntity);

		if (this.mChildren == null) {
			this.allocateChildren();
		}
		if (pIndex < 0) {
			this.mChildren.add(pEntity);
		} else {
			this.mChildren.add(pIndex, pEntity);
		}
		pEntity.setParent(this);
		pEntity.onAttached();
	}

	@Override
	public void sortChildren() {
		this.sortChildren(true);
	}

	@Override
	public void sortChildren(final boolean pImmediate) {
		if (this.mChildren == null) {
			return;
		}
		if (pImmediate) {
			ZIndexSorter.getInstance().sort(this.mChildren);
		} else {
			this.mChildrenSortPending = true;
		}
	}

	@Override
	public void sortChildren(final IEntityComparator pEntityComparator) {
		if (this.mChildren == null) {
			return;
		}
		ZIndexSorter.getInstance().sort(this.mChildren, pEntityComparator);
	}

	@Override
	public boolean detachChild(final IEntity pEntity) {
		if (this.mChildren == null) {
			return false;
		}
		return this.mChildren.remove(pEntity, BaseEntityGroup.PARAMETERCALLABLE_DETACHCHILD);
	}

	@Override
	public IEntity detachChild(final int pTag) {
		if (this.mChildren == null) {
			return null;
		}
		for (int i = this.mChildren.size() - 1; i >= 0; i--) {
			if (this.mChildren.get(i).getTag() == pTag) {
				final IEntity removed = this.mChildren.remove(i);
				BaseEntityGroup.PARAMETERCALLABLE_DETACHCHILD.call(removed);
				return removed;
			}
		}
		return null;
	}

	@Override
	public IEntity detachChild(final IEntityMatcher pEntityMatcher) {
		if (this.mChildren == null) {
			return null;
		}
		return this.mChildren.remove(pEntityMatcher, BaseEntityGroup.PARAMETERCALLABLE_DETACHCHILD);
	}

	@Override
	public boolean detachChildren(final IEntityMatcher pEntityMatcher) {
		if (this.mChildren == null) {
			return false;
		}
		return this.mChildren.removeAll(pEntityMatcher, BaseEntityGroup.PARAMETERCALLABLE_DETACHCHILD);
	}

	@Override
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable) {
		if (this.mChildren == null) {
			return;
		}
		this.mChildren.call(pEntityParameterCallable);
	}

	@Override
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable, final IEntityMatcher pEntityMatcher) {
		if (this.mChildren == null) {
			return;
		}
		this.mChildren.call(pEntityMatcher, pEntityParameterCallable);
	}

	private void assertEntityHasNoParent(final IEntity pEntity) throws IllegalStateException {
		if (pEntity.hasParent()) {
			final String entityClassName = pEntity.getClass().getSimpleName();
			final String currentParentClassName = pEntity.getParent().getClass().getSimpleName();
			final String newParentClassName = this.getClass().getSimpleName();
			throw new IllegalStateException("pEntity '" + entityClassName + "' already has a parent: '" + currentParentClassName + "'. New parent: '" + newParentClassName + "'!");
		}
	}

	private void allocateChildren() {
		this.mChildren = new SmartList<IEntity>(BaseEntityGroup.CHILDREN_CAPACITY_DEFAULT);
	}

	// ===========================================================
	// 父类方法
	// ===========================================================

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		super.reset();
		this.mChildrenVisible = true;
		this.mChildrenIgnoreUpdate = false;

		if (this.mChildren != null) {
			final SmartList<IEntity> entities = this.mChildren;
			for (int i = entities.size() - 1; i >= 0; i--) {
				entities.get(i).reset();
			}
		}
	}

	@Override
	public void toString(final StringBuilder pStringBuilder) {
		pStringBuilder.append(this.getClass().getSimpleName());

		if ((this.mChildren != null) && (this.mChildren.size() > 0)) {
			pStringBuilder.append(" [");
			final SmartList<IEntity> entities = this.mChildren;
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).toString(pStringBuilder);
				if (i < (entities.size() - 1)) {
					pStringBuilder.append(", ");
				}
			}
			pStringBuilder.append("]");
		}
	}

	public void onManagedDraw(final GLState pGLState, final Camera pCamera) {
		if (!this.mClippingEnabled || this.getWidth() >= Size.SIZE_INFINITE || this.getHeight() >= Size.SIZE_INFINITE) {
			this.drawEntity(pGLState, pCamera);
		} else {
			final float cameraFactorX = pCamera.getSurfaceWidth() / pCamera.getWidth();
			final float cameraFactorY = pCamera.getSurfaceHeight() / pCamera.getHeight();
			final float[] ltPosition = this.convertLocalToSceneCoordinates(0, 0);
			final float left = ltPosition[0] * cameraFactorX;
			final float top = ltPosition[1] * cameraFactorY;
			final float[] tbPosition = this.convertLocalToSceneCoordinates(this.getWidth(), this.getHeight());
			final float right = tbPosition[0] * cameraFactorX;
			final float bottom = tbPosition[1] * cameraFactorY;
			final float realWidth = right - left;
			final float realHeight = bottom - top;
			// 区域绘制处理
			pGLState.pushProjectionGLMatrix();
			boolean alreadyEnable = pGLState.enableScissorTest();
			GLES20.glScissor(Math.round(left), Math.round(pCamera.getSurfaceHeight() - bottom), Math.round(realWidth), Math.round(realHeight));
			// /draw self and children
			this.drawEntity(pGLState, pCamera);
			GLES20.glScissor(Math.round(left), Math.round(pCamera.getSurfaceHeight() - bottom), Math.round(realWidth), Math.round(realHeight));
			// 区域绘制完成
			if (!alreadyEnable) {
				pGLState.disableScissorTest();
			}
			pGLState.popProjectionGLMatrix();
		}
	}

	private void drawEntity(final GLState pGLState, final Camera pCamera) {
		pGLState.pushModelViewGLMatrix();
		{
			this.onApplyTransformations(pGLState);

			final SmartList<IEntity> children = this.mChildren;
			if ((children == null) || !this.mChildrenVisible) {
				/* Draw only self. */
				this.preDraw(pGLState, pCamera);
				this.draw(pGLState, pCamera);
				this.postDraw(pGLState, pCamera);
			} else {
				if (this.mChildrenSortPending) {
					ZIndexSorter.getInstance().sort(this.mChildren);
					this.mChildrenSortPending = false;
				}

				final int childCount = children.size();
				int i = 0;

				{ /* Draw children behind this Entity. */
					for (; i < childCount; i++) {
						final IEntity child = children.get(i);
						if (child != null && child.getZIndex() < 0) {
							child.onDraw(pGLState, pCamera);
						} else {
							break;
						}
					}
				}

				/* Draw self. */
				this.preDraw(pGLState, pCamera);
				this.draw(pGLState, pCamera);
				this.postDraw(pGLState, pCamera);

				{ /* Draw children in front of this Entity. */
					for (; i < childCount; i++) {
						final IEntity child = children.get(i);
						if (child != null) {
							child.onDraw(pGLState, pCamera);
						}
					}
				}
			}
		}
		pGLState.popModelViewGLMatrix();
	}

	@Override
	public void onManagedUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		super.onManagedUpdate(pSecondsElapsed);
		if ((this.mChildren != null) && !this.mChildrenIgnoreUpdate) {
			final SmartList<IEntity> entities = this.mChildren;
			final int entityCount = entities.size();
			for (int i = 0; i < entityCount; i++) {
				final IEntity child = entities.get(i);
				if (child != null) {
					child.onUpdate(pSecondsElapsed);
				}
			}
		}
	}

	@Override
	public boolean onTouch(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		if (this.mChildren != null && !this.mChildrenIgnoreUpdate && this.mChildrenVisible) {
			final int action = pSceneTouchEvent.getAction();
			final boolean isActionDown = pSceneTouchEvent.isActionDown();
			final boolean isActionMove = pSceneTouchEvent.isActionMove();
			if (!isActionDown) {
				final SparseArray<IEntity> touchAreaBindings = this.mTouchAreaBindings;
				final IEntity boundTouchArea = touchAreaBindings.get(pSceneTouchEvent.getPointerID());
				/*
				 * In the case a ITouchArea has been bound to this PointerID,
				 * we'll pass this this TouchEvent to the same ITouchArea.
				 */
				if (boundTouchArea != null) {
					/* Check if boundTouchArea needs to be removed. */
					switch (action) {
					case TouchEvent.ACTION_UP:
					case TouchEvent.ACTION_CANCEL:
						touchAreaBindings.remove(pSceneTouchEvent.getPointerID());
					}
					if (this.isVisible()) {
						final boolean handled = boundTouchArea.onTouch(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
						if (handled) {
							return true;
						}
					}
				}
			}

			if (this.isVisible()) {
				final SmartList<IEntity> entities = this.mChildren;
				final int entityCount = entities.size();
				for (int i = entityCount - 1; i >= 0; i--) {
					final IEntity child = entities.get(i);
					if (child != null) {
						if (child.onTouch(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)) {
							if (isActionDown || isActionMove) {
								this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), child);
							}
							return true;
						}
					}
				}
			}
		}
		return super.onTouch(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}

	@Override
	public void setAlpha(float pAlpha) {
		// TODO Auto-generated method stub
		super.setAlpha(pAlpha);
		if (this.mChildren != null) {
			final SmartList<IEntity> entities = this.mChildren;
			final int entityCount = entities.size();
			for (int i = 0; i < entityCount; i++) {
				final IEntity child = entities.get(i);
				if (child != null) {
					child.setAlpha(child.getAlpha());
				}
			}
		}
	}

	// ===========================================================
	// 布局处理
	// ===========================================================

	@Override
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		//
		// if (this.getWidth() < pWidthMeasureSpec) {
		// pWidthMeasureSpec = this.getWidth();
		// }
		// if (this.getHeight() < pHeightMeasureSpec) {
		// pHeightMeasureSpec = this.getHeight();
		// }
		this.setMeasuredDimension(pWidthMeasureSpec, pHeightMeasureSpec);
		//
		int childCount = this.getChildCount();
		for (int i = childCount - 1; i >= 0; i--) {
			IEntity child = this.getChildByIndex(i);
			if (child != null) {
				child.measure(pWidthMeasureSpec, pHeightMeasureSpec);
			}
		}
	}

	@Override
	public void onLayout() {
		// TODO Auto-generated method stub
		super.onLayout();
		int childCount = this.getChildCount();
		for (int i = childCount - 1; i >= 0; i--) {
			IEntity child = this.getChildByIndex(i);
			if (child != null) {
				child.layout();
			}
		}
	}

	@Override
	public void onDrawRectChange() {
		// TODO Auto-generated method stub
		int childCount = this.getChildCount();
		for (int i = childCount - 1; i >= 0; i--) {
			IEntity child = this.getChildByIndex(i);
			if (child != null) {
				child.onDrawRectChange();
			}
		}
		super.onDrawRectChange();
	}

	// ===========================================================
	// 方法
	// ===========================================================

	/**
	 * 自动计算成自适应宽高
	 */
	public void setWrapSize() {
		float minX = 0.0f;
		float minY = 0.0f;
		float maxX = 0.0f;
		float maxY = 0.0f;
		int childCount = this.getChildCount();
		for (int i = childCount - 1; i >= 0; i--) {
			Entity child = (Entity) this.getChildByIndex(i);
			if (child != null) {
				if (child.getX() < minX) {
					minX = child.getX();
				}
				if (child.getRightX() > maxX) {
					maxX = child.getRightX();
				}
				if (child.getY() < minY) {
					minY = child.getY();
				}
				if (child.getBottomY() > maxY) {
					maxY = child.getBottomY();
				}
			}
		}
		this.setWidth(maxX - minX);
		this.setHeight(maxY - minY);
	}
	
	/**
	 * 恢复Children到0.0坐标
	 */
	public void resetChildrenToZeroCoordinates(){
		float minX = 0.0f;
		float minY = 0.0f;
		int childCount = this.getChildCount();
		for (int i = childCount - 1; i >= 0; i--) {
			Entity child = (Entity) this.getChildByIndex(i);
			if (child != null) {
				if (child.getX() < minX) {
					minX = child.getX();
				}
				if (child.getY() < minY) {
					minY = child.getY();
				}
			}
		}
		for (int i = childCount - 1; i >= 0; i--) {
			Entity child = (Entity) this.getChildByIndex(i);
			if (child != null) {
				child.setPosition(child.getX() + Math.abs(minX), child.getY() + Math.abs(minY));
			}
		}
	}

	// ===========================================================
	// Getter And Setter
	// ===========================================================

	public boolean isClippingEnabled() {
		return this.mClippingEnabled;
	}

	public void setClippingEnabled(final boolean pClippingEnabled) {
		this.mClippingEnabled = pClippingEnabled;
	}

}
