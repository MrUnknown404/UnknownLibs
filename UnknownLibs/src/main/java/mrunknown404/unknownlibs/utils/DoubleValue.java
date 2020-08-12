package mrunknown404.unknownlibs.utils;

/**A class that holds 2 unique values
 * @since 1.0.0
 * @author -Unknown-
 * 
 * @param <L> L
 * @param <R> R
 */
@SuppressWarnings("javadoc")
public class DoubleValue<L, R> {
	private L left;
	private R right;
	
	public DoubleValue(L left, R right) {
		this.left = left;
		this.right = right;
	}
	
	public DoubleValue<L, R> setL(L left) {
		this.left = left;
		return this;
	}
	
	public DoubleValue<L, R> setR(R right) {
		this.right = right;
		return this;
	}
	
	public DoubleValue<L, R> setLeft(L left) {
		setL(left);
		return this;
	}
	
	public DoubleValue<L, R> setRight(R right) {
		setR(right);
		return this;
	}
	
	public L getL() {
		return left;
	}
	
	public R getR() {
		return right;
	}
	
	public L getLeft() {
		return getL();
	}
	
	public R getRight() {
		return getR();
	}
	
	@Override
	public String toString() {
		return "(" + left + ", " + right + ")";
	}
}
