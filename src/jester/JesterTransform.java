package jester;

/**
 * Represents a 2D transformation using a 3x3 matrix.
 * This class provides methods for translation, rotation, scaling, and other transformations.
 */
public class JesterTransform {

    private float[] matrix; // 3x3 matrix stored in row-major order

    public JesterVector2 position;
    public float rotation;
    public JesterVector2 scale;

    /**
     * Constructs a new JesterTransform and initializes it to the identity transformation.
     */
    public JesterTransform() {
        identity();
    }

    /**
     * Resets the transformation to the identity matrix.
     */
    public void identity() {
        position = new JesterVector2(0, 0);
        rotation = 0f;
        scale = new JesterVector2(1, 1);

        matrix = new float[]{
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        };
    }

    /**
     * Resets the transformation to the identity matrix.
     */
    public void reset() {
        identity();
    }

    /**
     * Translates the transformation by the specified x and y values.
     * @param x The amount to translate in the x direction.
     * @param y The amount to translate in the y direction.
     */
    public void translate(float x, float y) {
//        position.x += x;
//        position.y += y;
        position = new JesterVector2(position.getX() + x, position.getY() + y);
        matrix[6] += x;
        matrix[7] += y;
    }

    /**
     * Rotates the transformation by the specified angle in radians.
     * @param radians The angle to rotate in radians.
     */
    public void rotate(float radians) {
        rotation += radians;

        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        float m0 = matrix[0] * cos + matrix[3] * sin;
        float m1 = matrix[1] * cos + matrix[4] * sin;
        float m3 = matrix[0] * -sin + matrix[3] * cos;
        float m4 = matrix[1] * -sin + matrix[4] * cos;

        matrix[0] = m0;
        matrix[1] = m1;
        matrix[3] = m3;
        matrix[4] = m4;
    }

    /**
     * Scales the transformation by the specified x and y factors.
     * @param sx The scaling factor in the x direction.
     * @param sy The scaling factor in the y direction.
     */
    public void scale(float sx, float sy) {
//        scale.x *= sx;
//        scale.y *= sy;
        scale = new JesterVector2(scale.getX() * sx, scale.getY() * sy);
        matrix[0] *= sx;
        matrix[1] *= sx;
        matrix[3] *= sy;
        matrix[4] *= sy;
    }

    /**
     * Applies shearing to the transformation by the specified x and y factors.
     * @param sx The shear factor in the x direction.
     * @param sy The shear factor in the y direction.
     */
    public void shear(float sx, float sy) {
        matrix[1] += matrix[0] * sy;
        matrix[3] += matrix[4] * sx;
    }

    /**
     * Applies the transformation to a given point.
     * @param point The point to transform.
     * @return A new JesterVector2 representing the transformed point.
     */
    public JesterVector2 apply(JesterVector2 point) {
        float x = (float) point.getX();
        float y = (float) point.getY();

        float newX = matrix[0] * x + matrix[3] * y + matrix[6];
        float newY = matrix[1] * x + matrix[4] * y + matrix[7];

        return new JesterVector2(newX, newY);
    }

    /**
     * Computes the inverse of the transformation.
     * @return A new JesterTransform representing the inverse transformation.
     * @throws RuntimeException if the determinant is too close to 0.
     */
    public JesterTransform inverse() {
        JesterTransform inv = new JesterTransform();

        float det = matrix[0] * matrix[4] - matrix[1] * matrix[3];
        if (Math.abs(det) < 1e-6) {
            throw new RuntimeException("Cannot invert transform: determinant is too close to 0");
        }
        float invDet = 1.0f / det;

        inv.matrix[0] = matrix[4] * invDet;
        inv.matrix[1] = -matrix[1] * invDet;
        inv.matrix[3] = -matrix[3] * invDet;
        inv.matrix[4] = matrix[0] * invDet;

        inv.matrix[6] = -(inv.matrix[0] * matrix[6] + inv.matrix[3] * matrix[7]);
        inv.matrix[7] = -(inv.matrix[1] * matrix[6] + inv.matrix[4] * matrix[7]);

        return inv;
    }

    /**
     * Applies the inverse transformation to a given point.
     * @param point The point to transform.
     * @return A new JesterVector2 representing the transformed point.
     */
    public JesterVector2 inverseTransformPoint(JesterVector2 point) {
        return inverse().apply(point);
    }

    /**
     * Gets the current transformation matrix.
     * @return A clone of the current transformation matrix.
     */
    public float[] getMatrix() {
        return matrix.clone();
    }

    /**
     * Sets the transformation matrix to a new matrix.
     * @param newMatrix The new matrix to set.
     * @throws IllegalArgumentException if the new matrix does not have 9 elements.
     */
    public void setMatrix(float[] newMatrix) {
        if (newMatrix.length != 9) {
            throw new IllegalArgumentException("Matrix must have 9 elements");
        }
        matrix = newMatrix.clone();
    }

    /**
     * Checks if the transformation is an affine 2D transformation.
     * @return true if the transformation is affine, false otherwise.
     */
    public boolean isAffine2DTransform() {
        // In 2D, affine transform should have last row [0, 0, 1]
        return matrix[2] == 0f && matrix[5] == 0f && matrix[8] == 1f;
    }

    /**
     * Sets the transformation using position, rotation, and scale.
     * @param posX The x position.
     * @param posY The y position.
     * @param rotationRadians The rotation in radians.
     * @param scaleX The scaling factor in the x direction.
     * @param scaleY The scaling factor in the y direction.
     */
    public void setTransformation(float posX, float posY, float rotationRadians, float scaleX, float scaleY) {
        identity();
        translate(posX, posY);
        rotate(rotationRadians);
        scale(scaleX, scaleY);
    }

    /**
     * Transforms a point using the current transformation matrix.
     * @param point The point to transform.
     * @return A new JesterVector2 representing the transformed point.
     */
    public JesterVector2 transformPoint(JesterVector2 point) {
        return apply(point);
    }
}