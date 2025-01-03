package ru.tbank.hse.fd.streamPractise.service;

import ru.tbank.hse.fd.streamPractise.model.Car;
import ru.tbank.hse.fd.streamPractise.model.CarInfo;
import ru.tbank.hse.fd.streamPractise.model.Owner;
import ru.tbank.hse.fd.streamPractise.utils.Condition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Необходимо реализовать каждый метод
 * <p>
 * Запрещено использовать if, for, foreach...
 * Все методы реализуются в одну строчку
 * Можно использовать только stream API
 *
 */
public class CarService {

    /**
     * Приходит список Car
     * Необходимо вернуть список строк из Condition
     */
    public List<String> getConditions(List<Car> cars) {
        return cars.stream().map(Car::getCondition).map(Condition::getText).toList();
    }

    /**
     * Приходит список Car
     * Необходимо вернуть только те, у которых Condition - "NEW"
     */
    public List<Car> getNewCars(List<Car> cars) {
        return cars.stream().filter(car -> car.getCondition().getText() == "NEW").toList();
    }

    /**
     * Приходит список Car
     * Необходимо вернуть количество Car, у которых больше 2 Owners
     */
    public long countCarsOwners(List<Car> cars) {
        return cars.stream().filter(car -> car.getOwners().size() > 2).count();
    }

    /**
     * Приходит список Car
     * Необходимо каждому элементу списка в поле age прибавить 1
     */
    public List<Car> incrementCarAge(List<Car> cars) {
        return cars.stream().peek(car -> car.setAge(car.getAge() + 1)).toList();
    }

    /**
     * Приходит список Car
     * Необходимо вернуть Car, у которого самое большое значение age
     */
    public Car getOldestCar(List<Car> cars) {
        return cars.stream().max(Comparator.comparing(Car::getAge)).orElseThrow(RuntimeException::new);
    }

    /**
     * Приходит список Car
     * Необходимо вернуть список имён всех владельцев
     * Имена не должны повторяться
     */
    public List<String> getOwnersCarsNames(List<Car> cars) {
        List<String> ow = new ArrayList<>();
        List<List<Owner>> aw = cars.stream().map(Car::getOwners).toList();
        for (List<Owner> el: aw){
            for (Owner el1: el) {
                ow.add(el1.getName());
            }
        }
        return ow.stream().distinct().toList(); // я не поняла как это по нормальному сделать
    }

    /**
     * Приходит список Car
     * Необходимо преобразовать его в список CarInfo
     */
    public List<CarInfo> mapToCarInfo(List<Car> cars) {
        //return cars.stream().map(Car::getName).map(Car::getAge).map(Car::getOwners).toList();   я не понимаю в чём тут проблема
        return null;
    }

    /**
     * Приходит список Car
     * Необходимо вернуть не более двух машин, у которых Condition - BROKEN
     */
    public List<Car> getTwoBrokenCar(List<Car> cars) {
        return cars.stream().filter(car -> car.getCondition().getText() == "BROKEN").limit(2).toList();
    }

    /**
     * Приходит список Car
     * Необходимо вернуть отсортированный по полю age список Car
     */
    public List<Car> getSortedCarsByAge(List<Car> cars) {
        return cars.stream().sorted(Comparator.comparing(car -> car.getAge())).toList();
    }

    /**
     * Приходит список Car
     * Необходимо посчитать средний возраст всех машин
     */
    public double getAvgCarsAge(List<Car> cars) {
        return cars.stream().map(Car::getAge).collect(Collectors.averagingDouble(Integer::intValue));
    }

    /**
     * Приходит список Car
     * Проверить, что все машины с Condition - "Broken" старше 10 лет
     */
    public Boolean checkBrokenCarsAge(List<Car> cars) {
        long a = cars.stream().filter(car -> car.getCondition().getText() == "BROKEN").filter(car -> car.getAge() > 10).count();
        long b = cars.stream().filter(car -> car.getCondition().getText() == "BROKEN").count();
        return a == b;
    }

    /**
     * Приходит список Car
     * Проверить, что хотя бы у одной машины с Condition - "USED" был владелец по имени Adam
     */
    public Boolean checkCarOwnerName(List<Car> cars) {
        List<Car> cars1 = cars.stream().filter(car -> car.getCondition().getText() == "USED").toList();
        List<List<Owner>> aw = cars1.stream().map(Car::getOwners).toList();
        for (List<Owner> el: aw){
            for (Owner el1: el) {
                if (el1.getName() == "Adam"){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Приходит список Car
     * Необходимо вернуть любого Owner старше 36 лет
     */
    public Owner getAnyOwner(List<Car> cars) {
        List<List<Owner>> aw = cars.stream().map(Car::getOwners).toList();
        for (List<Owner> el: aw){
            for (Owner el1: el) {
                if (el1.getAge() > 36){
                    return el1;
                }
            }
        }
        return null;
    }
}
