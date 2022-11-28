package local.mydomain.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {

    private static Set<IEmployee> employees;
    private static Map<String, Integer> salaryMap;

    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmerzzz, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=2630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=3}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=7}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        IEmployee employee = null;

        salaryMap = new LinkedHashMap<>();
        employees = new TreeSet<>((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            Employee emp = (Employee) employee;
            boolean add = employees.add(employee);
            salaryMap.put(emp.firstName,emp.getSalary());
//            salaryMap.putIfAbsent(emp.firstName,emp.getSalary());

         }

        for (IEmployee worker : employees) {
            System.out.println(worker.toString());
            totalSalaries+= worker.getSalary();
        }
//
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n",currencyInstance.format(totalSalaries));
        System.out.println(employees.size());
        System.out.println(salaryMap.values());//Output: [2803000, 0, 1823000, 1935000, 910350, 8000,
                                                // 6500, 3900, 3900, 7500, 7000, 2506, 2506, 2510, 2514, 1500000]
        System.out.println(salaryMap.keySet()); //Output: [Fred, N/A, Fred2, Fred3, Fred4, Fred5, Barney, Barney2,
                                                // Barney3, Barney4, Barney5, Wilma, Wilma2, Wilma3, Wilma5, Betty]
        System.out.println(salaryMap.entrySet()); //Output: [Fred=2803000, N/A=0, Fred2=1823000, Fred3=1935000,
                                                 // Fred4=910350, Fred5=8000, Barney=6500, Barney2=3900,
                                                 // Barney3=3900, Barney4=7500, Barney5=7000, Wilma=2506,
                                                 // Wilma2=2506, Wilma3=2510, Wilma5=2514, Betty=1500000]

        for (Map.Entry<String, Integer> entry : salaryMap.entrySet()) {
            System.out.printf("Key = %s, Value = %s%n", entry.getKey(), entry.getValue());
            //Output: Key = Fred, Value = 2803000
                        //Key = N/A, Value = 0
                        //Key = Fred2, Value = 1823000
                        //Key = Fred3, Value = 1935000
                        //Key = Fred4, Value = 910350
                        //Key = Fred5, Value = 8000
                        //Key = Barney, Value = 6500
                        //Key = Barney2, Value = 3900
                        //Key = Barney3, Value = 3900
                        //Key = Barney4, Value = 7500
                        //Key = Barney5, Value = 7000
                        //Key = Wilma, Value = 2506
                        //Key = Wilma2, Value = 2506
                        //Key = Wilma3, Value = 2510
                        //Key = Wilma5, Value = 2514
                        //Key = Betty, Value = 1500000
        }
    }


    private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
        for(Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            if (worker instanceof Employee tmpWorker) {
                if (removalNames.contains(tmpWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }

    public int getSalary(String firstName) {
//        Integer value = salaryMap.get(firstName);
//        if (value ==null) {
//            return -1;
//        }else {
//            return value;
//        }
        return salaryMap.getOrDefault(firstName,-1);
          }

}
