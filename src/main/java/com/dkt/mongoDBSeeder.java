package com.dkt;

//@Component
//public class mongoDBSeeder implements CommandLineRunner {
//
////    private EmployeeRepository employeeRepository;
//    private DepartmentRepository departmentRepository;
//
////    public mongoDBSeeder(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
////        this.employeeRepository = employeeRepository;
////        this.departmentRepository = departmentRepository;
////    }
//
//    public mongoDBSeeder(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
////        employeeRepository.deleteAll();
//        departmentRepository.deleteAll();
//
//        Employee employee1 = new Employee(2234, "dung", 21, 2400);
//        Employee employee2 = new Employee(2346, "thoa", 21, 3000);
//        Employee employee3 = new Employee(5323, "hung", 21, 6000);
//        Employee employee4 = new Employee(3432, "tung", 22, 2300);
//        Employee employee5 = new Employee(2543, "tu", 20, 25600);
//
////        employeeRepository.save(employee1);
////        employeeRepository.save(employee2);
////        employeeRepository.save(employee3);
////
////        employee1 = employeeRepository.findByEmployeeID(2234);
//
//        Department department1 = new Department(23, "Sale", "marketing");
//        Department department2 = new Department(34, "IT", "computer");
//        Department department3 = new Department(56, "Marketing", "PR");
//        Department department4 = new Department(12, "CEO", "manage");
//        Department department5 = new Department(78, "Security", "secure");
//        department1.addEmployee(employee1);
//        department2.addEmployee(employee2);
//        department2.addEmployee(employee3);
//        department2.addEmployee(employee4);
//        department2.addEmployee(employee5);
//
//        for(Employee e : department2.getEmployees())
//        {
//            System.out.println(e.getName());
//        }
//
//        departmentRepository.insert(department1);
//        departmentRepository.insert(department2);
//        departmentRepository.insert(department3);
//        departmentRepository.insert(department4);
//        departmentRepository.insert(department5);
//
////        department2 = departmentRepository.findDepartmentByDepartmentID(34);
//
////        employeeRepository.save(employee1);
//
//
//        System.out.println("ok");
//
//    }
//}
