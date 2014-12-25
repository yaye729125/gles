package com.orange.entity.group;

import java.util.Comparator;

import com.orange.engine.Engine;
import com.orange.engine.handler.runnable.RunnableHandler;
import com.orange.entity.IEntity;
import com.orange.entity.IEntityComparator;
import com.orange.entity.IEntityMatcher;
import com.orange.entity.IEntityParameterCallable;
import com.orange.entity.scene.BaseScene;
import com.orange.util.adt.list.SmartList;

/**
 * entity集合接口，包含所有对child操作的方法列举
 *
 */
public interface IEntityGroup extends IEntity{

	public boolean isChildrenVisible();
	public void setChildrenVisible(final boolean pChildrenVisible);

	public boolean isChildrenIgnoreUpdate();
	public void setChildrenIgnoreUpdate(boolean pChildrenIgnoreUpdate);

	public SmartList<IEntity> getChildren();
	public int getChildCount();
	public void attachChild(final IEntity pEntity);
	public void attachChild(final IEntity pEntity, int pIndex);
	public IEntity getChildByTag(final int pTag);
	public IEntity getChildByMatcher(final IEntityMatcher pEntityMatcher);
	public IEntity getChildByIndex(final int pIndex);
	public IEntity getFirstChild();
	public IEntity getLastChild();

	/**
	 * Immediately sorts the {@link IEntity}s based on their ZIndex. Sort is
	 * stable.
	 */
	public void sortChildren();

	/**
	 * Sorts the {@link IEntity}s based on their ZIndex. Sort is stable. In
	 * contrast to {@link IEntity#sortChildren()} this method is particularly
	 * useful to avoid multiple sorts per frame.
	 * 
	 * @param pImmediate
	 *            if <code>true</code>, the sorting is executed immediately. If
	 *            <code>false</code> the sorting is executed before the next
	 *            (visible) drawing of the children of this {@link IEntity}.
	 */
	public void sortChildren(final boolean pImmediate);

	/**
	 * Sorts the {@link IEntity}s based on the {@link Comparator} supplied. Sort
	 * is stable.
	 * 
	 * @param pEntityComparator
	 */
	public void sortChildren(final IEntityComparator pEntityComparator);

	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered to a
	 * {@link BaseScene} or the {@link Engine} itself, because otherwise it may
	 * throw an {@link IndexOutOfBoundsException} in the Update-Thread or the
	 * GL-Thread!</b>
	 */
	public boolean detachChild(final IEntity pEntity);

	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered to a
	 * {@link BaseScene} or the {@link Engine} itself, because otherwise it may
	 * throw an {@link IndexOutOfBoundsException} in the Update-Thread or the
	 * GL-Thread!</b>
	 */
	public IEntity detachChild(final int pTag);

	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered to a
	 * {@link BaseScene} or the {@link Engine} itself, because otherwise it may
	 * throw an {@link IndexOutOfBoundsException} in the Update-Thread or the
	 * GL-Thread!</b>
	 */
	public IEntity detachChild(final IEntityMatcher pEntityMatcher);

	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered to a
	 * {@link BaseScene} or the {@link Engine} itself, because otherwise it may
	 * throw an {@link IndexOutOfBoundsException} in the Update-Thread or the
	 * GL-Thread!</b>
	 */
	public boolean detachChildren(final IEntityMatcher pEntityMatcher);

	public void detachChildren();
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable);
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable, final IEntityMatcher pEntityMatcher);

}
