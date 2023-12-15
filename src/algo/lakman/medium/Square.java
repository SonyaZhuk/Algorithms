package algo.lakman.medium;

/**
 * Finds the line that split squares in half.
 * <p>
 * See Lakman p. 518
 */
public class Square {
    private double left;
    private double right;
    private double top;
    private double bottom;
    private double size;

    public Square(double left, double right, double top, double bottom, double size) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.size = size;
    }
    public Point middle() {
        return new Point((this.left + this.right) / 2.0,
                (this.top + this.bottom) / 2.0);
    }

    /* Возвращает точку, в которой отрезок, соединяющий midl и mid2,
       пересекает сторону квадрата 1. То есть мы проводим линию из mid2
       в midl и продолжаем ее до стороны квадрата. */
    public Point extend(Point midl, Point mid2, double size) {
        /* Определение направления, в котором идет линия mid2 -> midl. */
        double xDir = midl.getX() < mid2.getX() ? -1 : 1;
        double yDir = midl.getY() < mid2.getY() ? -1 : 1;

         /* Если у midl и mid2 значения х совпадают, при вычислении наклона
            произойдет деление на 0. Этот случай обрабатывается отдельно. */
        if (midl.getX() == mid2.getX()) {
            return new Point(midl.getX(), midl.getY() + yDir * size / 2.0);
        }

        double slope = (midl.getY() - mid2.getX()) / (midl.getX() - mid2.getX());
        double xl = 0;
        double yl = 0;
         /* Наклон вычисляется по формуле (yl - у2} / (xl - х2).
            Примечание: при "крутом" наклоне (>1) конец отрезка
            пересечет горизонтальную сторону квадрата. При "пологом"
            наклоне (<1) конец отрезка пересечет вертикальную сторону квадрата . */
        if (Math.abs(slope) == 1) {
            xl = midl.getX() + xDir * size / 2.0;
            yl = midl.getY() + yDir * size / 2.0;
        } else if (Math.abs(slope) < 1) { // Пологий наклон
            xl = midl.getX() + xDir * size / 2.0;
            yl = slope * (xl - midl.getX()) + midl.getY();
        } else { // steep slope
            yl = midl.getY() + yDir * size / 2.0;
            xl = (yl - midl.getY()) / slope + midl.getX();
        }
        return new Point(xl, yl);
    }

    public Line cut(Square other) {
        /* Вычисление точек пересечения линии, соединяющей середины, со сторонами квадратов. */
        final Point p1 = extend(this.middle(), other.middle(), this.size);
        final Point p2 = extend(this.middle(), other.middle(), -1 * this.size);
        final Point p3 = extend(other.middle(), this.middle(), other.size);
        final Point p4 = extend(other.middle(), this.middle(), -1 * other.size);

         /* Определение начала и конца отрезков. Начальная точка находится
              левее остальных (и выше при совпадении), а конечная - правее
              остальных (и ниже при совпадении). */
        Point start = p1;
        Point end = p1;
        Point[] points = {p2, p3, p4};
        for (int i = 0; i < points.length; i++) {
            if (points[i].getX() < start.getX() ||
                    (points[i].getX() == start.getX() && points[i].getY() < start.getY())) {
                start = points[i];
            } else if (points[i].getX() > end.getX() ||
                    (points[i].getX() == end.getX() && points[i].getY() > end.getY())) {
                end = points[i];
            }
        }
        return new Line(start, end);
    }
}