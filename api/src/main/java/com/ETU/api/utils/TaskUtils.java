package com.etu.api.utils;

import com.etu.api.entities.Point;
import com.etu.api.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskUtils {
    /** Поиск процента заполнения многоугольника вторым многоугольником */
    public double overlapTaskSolution(Task task, List<Point> solutionPoints) {
        List<Point> taskPoints = task.getPoints();
        if (taskPoints.isEmpty()) {
            return 0D;
        }
        // Находим пересечение
        List<Point> intersection = findIntersection(taskPoints, solutionPoints);
        if (intersection.isEmpty()) {
            return 0D;
        }

        // Находим площадь пересечения
        double intersectionArea = calculateArea(intersection);
        double modelArea = calculateArea(taskPoints);
        // Находим процент совпадения площади пересечения и площади образцового многоугольника
        Double matchPercentage = (intersectionArea / modelArea) * 100; // Процент совпадения
        if (matchPercentage.isNaN()) matchPercentage = 0D;
        return matchPercentage;
    }

    /** Поиск пересечения многоугольников */
    private List<Point> findIntersection(List<Point> firstArea, List<Point> secondArea){
        // По умолчанию принимаем, что точки многоугольников расположены в нужном порядке

        List<Point> intersection = new ArrayList<>();

        // Находим все точки пересечения многоугольников и заносим их в результат
        // Проход по всем ребрам первого многоугольника
        for(int i=0; i<firstArea.size(); i++){
            int next_i = (i+1)%firstArea.size();
            // Проход по всем ребрам второго многоугольника
            for(int j=0; j<secondArea.size(); j++){
                int next_j = (j+1)% firstArea.size();
                List<Point> a = List.of(firstArea.get(i), firstArea.get(next_i));
                List<Point> b = List.of(secondArea.get(j), secondArea.get(next_j));
                Point interPoint = cross(a, b);

                if(interPoint!=null){
                    intersection.add(interPoint);
                }
            }
        }

        return intersection;
    }

    /** Поиск точки пересечения двух отрезков */
    private Point cross(List<Point> first, List<Point> second){
        double a = first.get(0).getY() - first.get(1).getY();
        double b = first.get(1).getX() - first.get(0).getX();
        double c = first.get(0).getX()* first.get(1).getY() - first.get(1).getX()* first.get(0).getY();
        double k = second.get(0).getY() - second.get(1).getY();
        double l = second.get(1).getX() - second.get(0).getX();
        double m = second.get(0).getX()* second.get(1).getY() - second.get(1).getX()* second.get(0).getY();

        Double y = (k*c-m*a)/(a*l-k*b);
        Double x = (-b*y-c)/a;

        if(y.isNaN() || y.isInfinite()){
            return null;
        } else if (x.isNaN() || x.isInfinite()) {
            return null;
        }

        double maxAX = Arrays.stream(new double[]{first.get(0).getX(), first.get(1).getX()}).max().orElse(Double.NaN);
        double minAX = Arrays.stream(new double[]{first.get(0).getX(), first.get(1).getX()}).min().orElse(Double.NaN);
        double maxAY = Arrays.stream(new double[]{first.get(0).getY(), first.get(1).getY()}).max().orElse(Double.NaN);
        double minAY = Arrays.stream(new double[]{first.get(0).getY(), first.get(1).getY()}).min().orElse(Double.NaN);

        double maxBX = Arrays.stream(new double[]{second.get(0).getX(), second.get(1).getX()}).max().orElse(Double.NaN);
        double minBX = Arrays.stream(new double[]{second.get(0).getX(), second.get(1).getX()}).min().orElse(Double.NaN);
        double maxBY = Arrays.stream(new double[]{second.get(0).getY(), second.get(1).getY()}).max().orElse(Double.NaN);
        double minBY = Arrays.stream(new double[]{second.get(0).getY(), second.get(1).getY()}).min().orElse(Double.NaN);

        // Если точка лежит в пределах первого отрезка
        if(minAX<=x && x<=maxAX && minAY<=y && y<=maxAY) {
            // Если точка лежит в пределах второго отрезка
            if(minBX<=x && x<=maxBX && minBY<=y && y<=maxBY){
                return new Point(0, x.floatValue(), y.floatValue());
            }
        }

        return null;
    }

    /** Поиск площади многоугольника*/
    private double calculateArea(List<Point> points){
        int n = points.size();
        double area = 0;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += (points.get(j).getX() - points.get(i).getX()) * (points.get(i).getY() + points.get(j).getY());
        }

        return Math.abs(area / 2);
    }

    public double linearTaskSolution(Task task, List<Point> solutionPoints){
        int count_right = 0;
        for(int j=0; j<task.getPoints().size(); j++){
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
        Double result = (double)count_right/(double)(task.getPoints().size())*100;
        if (result.isNaN()) result = 0D;
        return result;
    }
}
