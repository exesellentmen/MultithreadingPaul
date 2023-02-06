package com.paul.startclass.controllers;

import com.paul.startclass.models.Student;
import com.paul.startclass.repository.StudentRepository;
import com.paul.startclass.services.AsyncTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
@EnableAsync
@Controller
public class MultithreadingController {

    @Autowired
    StudentRepository studentRepository;

    //  ----- 1 Сгенерировать 40000 записей для начала тестирования, автоматически будет создана таблица Student  -----
    @GetMapping("/generate-rows")
    public String generateRows(){
        ArrayList<Student> students = new ArrayList<>();
        for (int i=0; i<40000; i++){
            Student student = new Student();
            student.setName("Student" + i);
            students.add(student);
        }
        this.studentRepository.saveAll(students);
        return "test";
    }

    // Удалить все записи
    @GetMapping("/remove-all-rows")
    public String removeAllRows(){
        this.studentRepository.deleteAll();
        return "test";
    }

    //  ----- 2 Добавить префиксы без многопоточности, примерное время для 40000 записей 6 секунд -----
    @GetMapping("/singlethreading-add-prefix")
    public String singlethreadingAddPrefix(){
        Iterable<Student> students = this.studentRepository.findAll();
        long startTime = System.currentTimeMillis();
        System.out.println("Time: " + System.currentTimeMillis());

        StreamSupport.stream(students.spliterator(),false)
                .forEach(student -> student.setName(student.getName() + " prefix1"));
        this.studentRepository.saveAll(students);

        long finishTime = System.currentTimeMillis();
        System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
        return "test";
    }

    //  ----- 3 Удаление префиксов без многопоточности, время выполнения для 40000 записей 5 секунд -----
    @GetMapping("/singlethreading-delete-prefix")
    public String singlethreadingDeletePrefix(Model model){
        Iterable<Student> students = this.studentRepository.findAll();
        long startTime = System.currentTimeMillis();

        StreamSupport.stream(students.spliterator(),false)
                .forEach(student -> student.setName(student.getName().replace(" prefix1","")));
        this.studentRepository.saveAll(students);

        long finishTime = System.currentTimeMillis();
        System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
        return "test";
    }

    //  ----- 4 Добавление префиксов с помощью многопоточности через Thread(), лучшее время для 40000 записей 2 секунды -----
    @GetMapping("/multithreading-add-prefix")
    public String multithreadingAddPrefix(){
        ArrayList<Student> students = (ArrayList<Student>) this.studentRepository.findAll();
        Long startTime = System.currentTimeMillis();

        Integer countRows = students.size();
        Integer countElementsOnThread = 5000;
        Integer pages = countRows / countElementsOnThread + 1;
        StudentRepository studentRepository = this.studentRepository;

        for(int n = 0; n < pages; n++){
            Integer i = n;

            Thread thread = new Thread(){
                public void run() {
                    List<Student> studentsThread = students.stream()
                            .skip(countElementsOnThread * i)
                            .limit(countElementsOnThread)
                            .peek(bIblockElement -> bIblockElement.setName(bIblockElement.getName() + " prefix1"))
                            .collect(Collectors.toList());
                    studentRepository.saveAll(studentsThread);
                    long finishTime = System.currentTimeMillis();
                    System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
                }
            };
            thread.start();

        }
        return "test";
    }



    //  ----- 5 Добавление префиксов с помощью многопоточности через Runnable(), лучшее время для 40000 записей 2 секунды -----
    @GetMapping("/multithreading-via-runnable-add-prefix")
    public String multithreadingViaRunnableAddPrefix(){
        ArrayList<Student> students = (ArrayList<Student>) this.studentRepository.findAll();
        Long startTime = System.currentTimeMillis();

        Integer countRows = students.size();
        Integer countElementsOnThread = 5000;
        Integer pages = countRows / countElementsOnThread + 1;
        StudentRepository studentRepository = this.studentRepository;

        for(int n = 0; n < pages; n++){
            Integer i = n;

            Runnable runnable = () -> {
                List<Student> studentsThread = students.stream()
                        .skip(countElementsOnThread * i)
                        .limit(countElementsOnThread)
                        .peek(bIblockElement -> bIblockElement.setName(bIblockElement.getName() + " prefix1"))
                        .collect(Collectors.toList());
                studentRepository.saveAll(studentsThread);
                long finishTime = System.currentTimeMillis();
                System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
            };

            Thread thread = new Thread(runnable);
            thread.start();

        }
        return "test";
    }




    //  ----- 6 Добавление префиксов через пул потоков, лучшее время для 40000 записей 2 секунды, выгодно когда мы знаем максимальное доступное количество потоков -----
    @GetMapping("/multithreading-thread-pool")
    public String multithreadingThreadPool(){
        ArrayList<Student> students = (ArrayList<Student>) this.studentRepository.findAll();
        Long startTime = System.currentTimeMillis();


        int countOfThreads = 8;
        Integer countRows = students.size();
        Integer countElementsOnThread = 5000;
        Integer pages = countRows / countElementsOnThread + 1;
        StudentRepository studentRepository = this.studentRepository;

        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);

        for(int n = 0; n < pages; n++){
            Integer i = n;
            Runnable runnable = () -> {
                List<Student> studentsThread = students.stream()
                        .skip(countElementsOnThread * i)
                        .limit(countElementsOnThread)
                        .peek(bIblockElement -> bIblockElement.setName(bIblockElement.getName() + " prefix1"))
                        .collect(Collectors.toList());
                studentRepository.saveAll(studentsThread);
                long finishTime = System.currentTimeMillis();
                System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
            };
            executorService.execute(runnable);
        }
        // После выполнения всех задач закрываем executorService
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            long finishTime = System.currentTimeMillis();
            System.out.println("executorService completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
        }

        return "test";
    }

    @Autowired
    ApplicationContext context;

    //  ----- 7 Тестирую асинхронность и многопоточность через аннотацию @Async -----
    @GetMapping("/async")
    public String mathodeAsync() throws InterruptedException {
        AsyncTester asyncTester = this.context.getBean(AsyncTester.class);
        for (int i = 1; i < 10; i++){
                asyncTester.saveTest(i);
        }
        return "test";
    }



    //  ----- 8 Тестирую Semaphore -----
    // В этом примере я разрешаю работать с определенном участком кода только для 8 потоков из 40 одновременно
    @GetMapping("/multithreading-semaphore")
    public String multithreadingSemaphore(){
        Semaphore semaphore = new Semaphore(8);

        ArrayList<Student> students = (ArrayList<Student>) this.studentRepository.findAll();
        Long startTime = System.currentTimeMillis();

        int countOfThreads = 40;
        Integer countElementsOnThread = 1000;

        Integer countRows = students.size();
        Integer pages = countRows / countElementsOnThread + 1;
        StudentRepository studentRepository = this.studentRepository;


        for(int n = 0; n < pages; n++) {
            Integer i = n;
            Thread thread = new Thread(){
                public void run(){
                    try {
                        semaphore.acquire();
                        System.out.println("started " + Thread.currentThread().getName());


                        List<Student> studentsThread = students.stream()
                                .skip(countElementsOnThread * i)
                                .limit(countElementsOnThread)
                                .peek(bIblockElement -> bIblockElement.setName(bIblockElement.getName() + " prefix1"))
                                .collect(Collectors.toList());
                        studentRepository.saveAll(studentsThread);


                        long finishTime = System.currentTimeMillis();
                        System.out.println("Operation completed, Time = " + ((finishTime - startTime) / 1000) + " seconds");
                        System.out.println("finished " + Thread.currentThread().getName());
                        semaphore.release();
                    } catch (InterruptedException e) {
                        System.out.println("We already have very many threads for executing");
                    }

                }
            };
            thread.start();
        }
        return "test";
    }


    // ----- 9 Тестирование Mutex -----
    // С помощью synchronized с участком когда может работать только 1 поток
    @GetMapping("/multithreading-mutex")
    public String multithreadingMutex(){
        Integer resource = 1;
        for (int i = 0; i < 8; i++){
            Thread thread = new Thread(){
                public void run(){
                    System.out.println(Thread.currentThread().getName() + " начался");
                    synchronized (resource){
                        System.out.println(Thread.currentThread().getName() + " получил доступ");
                        try {

                            System.out.println(Thread.currentThread().getName() + "  "+ resource);
                            Thread.sleep(100);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "освободил доступ");
                    }
                }
            };
            thread.start();
        }
        return "test";
    }

    // ----- 10 Тестирую Locker, ReentrantLock, Lock Concept ---------
    // Чем-то схож с synchronized, но иногда помогает избежать DeadLock
    @GetMapping("/multithreading-lock")
    public String multithreadingLocker(){
        ReentrantLock locker = new ReentrantLock();

//        System.out.println("Обычный режим вывода названий потоков");
//        for(int i = 0; i<5; i++){
//            Thread thread = new Thread(){
//                public void run(){
//                    for(int i = 1; i<5; i++){
//                        System.out.println(Thread.currentThread().getName());
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            };
//            thread.start();
//        }

        System.out.println("Вывод потоков с локером");
        for(int i = 0; i<5; i++){
            Thread thread = new Thread(){
                public void run(){
                    locker.lock();
                    for(int i = 1; i<5; i++){
                        System.out.println(Thread.currentThread().getName());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    locker.unlock();
                }
            };
            thread.start();
        }

//        Результат такой, поочередно работает каждый поток и переходит к следующему потоку
//        Thread-1
//        Thread-1
//        Thread-1
//        Thread-1
//        Thread-2
//        Thread-2
//        Thread-2
//        Thread-2
//        Thread-3

        return "Test";
    }



    // Пример DeadLock, в результате не будет выполнен перевод, т к 2 потока взаимно заблокировали друг друга
    @GetMapping("/multithreading-deadlock")
    public String multithreadingDeadLock(){

        Account accauntA = new Account();
        accauntA.count = 10;
        Account accauntB = new Account();
        accauntB.count = 10;
        Bank bank = new Bank();

        Thread threadA = new Thread(){public void run(){
            try {
                bank.transferMoney(accauntA, accauntB, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }};
        threadA.start();
        Thread threadB = new Thread(){public void run(){
            try {
                bank.transferMoney(accauntB, accauntA, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }};
        threadB.start();

        return "Test";
    }

    // Решение Deadlock
    @GetMapping("/multithreading-solution-deadlock")
    public String multithreadingSolutionDeadLock(){

        Account accauntA = new Account();
        accauntA.count = 10;
        Account accauntB = new Account();
        accauntB.count = 10;
        SmartBank bank = new SmartBank();

        Thread threadA = new Thread(() -> {
            try {
                bank.transferMoney(accauntA, accauntB, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        Thread threadB = new Thread(() -> {
            try {
                bank.transferMoney(accauntB, accauntA, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadB.start();

        return "Test";
    }

    // Решение Deadlock
    @GetMapping("/multithreading-super-solution-deadlock")
    public String multithreadingSuperSolutionDeadLock(){

        Account accauntA = new Account();
        accauntA.count = 10;
        accauntA.lock = new ReentrantLock();

        Account accauntB = new Account();
        accauntB.count = 10;
        accauntB.lock = new ReentrantLock();
        SuperSmartBank bank = new SuperSmartBank();

        Thread threadA = new Thread(() -> {
            try {
                bank.transferMoney(accauntA, accauntB, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        Thread threadB = new Thread(() -> {
            try {
                bank.transferMoney(accauntB, accauntA, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadB.start();

        return "Test";
    }

    class Account{
        public int count;
        public Lock lock;
    }

    class Bank{
        public void transferMoney(Account fromAccount, Account toAccount, int amount) throws InterruptedException {
            synchronized (fromAccount) {
                Thread.sleep(100);
                synchronized (toAccount) {
                    fromAccount.count = fromAccount.count - amount;
                    toAccount.count = toAccount.count + amount;
                    System.out.println("Operation's completed");
                }
            }
        }
    }


    class SmartBank{
        public void transferMoney(Account fromAccount, Account toAccount, int amount) throws InterruptedException {
            Account firstAccount, secondAccount;
            if(fromAccount.hashCode() > toAccount.hashCode()){
                firstAccount = fromAccount;
                secondAccount = toAccount;
            }else {
                firstAccount = toAccount;
                secondAccount = fromAccount;
            }

            synchronized (firstAccount) {
                Thread.sleep(100);
                synchronized (secondAccount) {
                    fromAccount.count = fromAccount.count - amount;
                    toAccount.count = toAccount.count + amount;
                    System.out.println("From Smart Bank Operation's completed");
                }
            }
        }
    }

    class SuperSmartBank{
        public void transferMoney(Account fromAccount, Account toAccount, int amount) throws InterruptedException {
            while(true) {
                if (fromAccount.lock.tryLock()) {
                    Thread.sleep(100);
                    if (toAccount.lock.tryLock()) {
                        try {
                            //do something
                            fromAccount.count = fromAccount.count - amount;
                            toAccount.count = toAccount.count + amount;
                            System.out.println(Thread.currentThread().getName()+" From Smart Bank Operation's completed");
                            break;
                        } finally {
                            toAccount.lock.unlock();
                            fromAccount.lock.unlock();
                        }
                    } else {
                        fromAccount.lock.unlock();
                    }
                }
                System.out.println("Attempt");
            }
        }
    }





    // Тестируем wait(), notify(), notifyAll()
    // Класс Магазин, хранящий произведенные товары
    class Store{
        private int product=0;
        public synchronized void get() {
            while (product<1) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                }
            }
            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);
            notify();
        }
        public synchronized void put() {
            while (product>=3) {
                try {
                    wait(); // Запускаем ожидание производителя и разрешаем есть потребителю
                }
                catch (InterruptedException e) {
                }
            }
            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            notify();
        }
    }
    // класс Производитель
    class Producer implements Runnable{

        Store store;
        Producer(Store store){
            this.store=store;
        }
        public void run(){
            for (int i = 1; i < 6; i++) {
                store.put();
            }
        }
    }
    // Класс Потребитель
    class Consumer implements Runnable{

        Store store;
        Consumer(Store store){
            this.store=store;
        }
        public void run(){
            for (int i = 1; i < 6; i++) {
                store.get();
            }
        }
    }

    // Тестируем wait(), notify(), notifyAll()
    @GetMapping("/multithreading-wait-etc")
    public String multithreadingWait(){

        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();

        return "test";
    }



}
