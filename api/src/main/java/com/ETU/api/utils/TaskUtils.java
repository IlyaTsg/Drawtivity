package com.etu.api.utils;

import com.etu.api.entities.Point;
import com.etu.api.entities.Task;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.List;

@Component
public class TaskUtils {
    /**
     * Поиск совпадения двух многоугольников
     *
     * @param task           задача, с точками которой происходит сравнение
     * @param solutionPoints точки второго многоугольника
     * @return процент совпадения
     */
    public double overlapTaskSolution(Task task, List<Point> solutionPoints) {
        // Принимаем, что перед вызовом функции проверено присутствие задачи в БД
        List<Point> taskPoints = task.getPoints();

        // Создаем Java-полигоны из исходных точек
        Polygon polygon1 = new Polygon();
        taskPoints.forEach(point -> polygon1.addPoint(point.getX().intValue(), point.getY().intValue()));
        Polygon polygon2 = new Polygon();
        solutionPoints.forEach(point -> polygon2.addPoint(point.getX().intValue(), point.getY().intValue()));

        // Для большего набора функций с многоугольниками создаем Java-обалсти
        Area area1 = new Area(polygon1);
        Area area2 = new Area(polygon2);

        // Находим область перечечения
        Area intersectionArea = new Area(area1);
        intersectionArea.intersect(area2);

        // Рассчитываем площади
        double area1Size = calculateArea(area1);
        double area2Size = calculateArea(area2);
        double intersectionAreaSize = calculateArea(intersectionArea);

        // Если выделена вся область пересечения совпадает с первой областью
        // И область выделения больше области пересечения
        // То сравниваем области пересечения и выделения
        // Тем самым накладываем штраф на лишнее выделение
        if (intersectionAreaSize != 0 && area1Size == intersectionAreaSize && area2Size > intersectionAreaSize) {
            return (intersectionAreaSize / area2Size) * 100.0;
        }

        // Сравнивем области пересечения и первой
        return (intersectionAreaSize / area1Size) * 100.0;
    }

    /**
     * Расчет площади области
     *
     * @param area область
     * @return значение площади
     */
    public static double calculateArea(Area area) {
        double areaSize = 0.0;
        PathIterator iterator = area.getPathIterator(null);
        double[] coords = new double[6];
        double startX = 0.0, startY = 0.0;
        double prevX = 0.0, prevY = 0.0;

        while (!iterator.isDone()) {
            int type = iterator.currentSegment(coords);

            switch (type) {
                case PathIterator.SEG_MOVETO -> {
                    startX = coords[0];
                    startY = coords[1];
                    prevX = startX;
                    prevY = startY;
                }
                case PathIterator.SEG_LINETO -> {
                    areaSize += (prevX * coords[1] - coords[0] * prevY);
                    prevX = coords[0];
                    prevY = coords[1];
                }
                case PathIterator.SEG_CLOSE -> areaSize += (prevX * startY - startX * prevY);
            }
            iterator.next();
        }
        return Math.abs(areaSize / 2.0);
    }

    /**
     * Поиск совпадения двух ломаных линий
     *
     * @param task           задача, с точками которой происходит сравнение
     * @param solutionPoints точки второй ломаной линии
     * @return процент совпадения
     */
    public double linearTaskSolution(Task task, List<Point> solutionPoints) {
        int count_right = 0;
        for (int j = 0; j < task.getPoints().size(); j++) {
            for (Point solutionPoint : solutionPoints) {
                double x_d = (solutionPoint.getX() - task.getPoints().get(j).getX()) * (solutionPoint.getX() - task.getPoints().get(j).getX());
                double y_d = (solutionPoint.getY() - task.getPoints().get(j).getY()) * (solutionPoint.getY() - task.getPoints().get(j).getY());
                double r_d = task.getDeviation() * task.getDeviation();
                if (x_d + y_d <= r_d) {
                    count_right += 1;
                    break;
                }
            }
        }
        Double result = (double) count_right / (double) (solutionPoints.size()) * 100;
        if (result.isNaN()) result = 0D;
        return result;
    }
}
