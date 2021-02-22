package com.example.demo;

import java.net.URISyntaxException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.JSONObject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	private final GenderRepository genderRepository;
	private final JobRepository jobRepository;
	private final EmployeeWorkedHoursRepository employeeWorkedHoursRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository, GenderRepository genderRepository,
			JobRepository jobRepository, EmployeeWorkedHoursRepository employeeWorkedHoursRepository) {
       this.employeeRepository = employeeRepository;
       this.genderRepository = genderRepository;
       this.jobRepository = jobRepository;
       this.employeeWorkedHoursRepository = employeeWorkedHoursRepository;
       
   }
	
	
	@PostMapping("/employee")
	HashMap<String, String> addEmployee(@RequestBody JSONObject inputJsonObj) throws URISyntaxException, ParseException {
        String name = (String) inputJsonObj.get("name");
        String lastName = (String) inputJsonObj.get("last_name");
        String date = (String)inputJsonObj.get("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date birthday = sdf.parse(date);
		
        String genderId = (String) inputJsonObj.get("gender_id");
        String jobId = (String) inputJsonObj.get("job_id");
        
        
        Gender gender = genderRepository.findById(Long.parseLong(genderId)).orElseThrow(() -> new GenderNotFoundException(Long.parseLong(genderId)));
        Job job = jobRepository.findById(Long.parseLong(jobId)).orElseThrow( () -> new JobNotFoundException(Long.parseLong(jobId)));;
       
        String generateId = null;
        String success = "false";
        
        
        /*CHECK birthdate*/
        Calendar cal = Calendar.getInstance(); // current date
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        
        cal.setTime(birthday); // now born date
        int years = currYear - cal.get(Calendar.YEAR);
        int bornMonth = cal.get(Calendar.MONTH);
        int yearsUser = 0;
        if (bornMonth == currMonth) { // same month
        	 yearsUser = cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years: years - 1;
        } 
        System.out.println(yearsUser);
        if (gender != null && job !=null && yearsUser >= 18) {
        	Employee employee = new Employee(name, lastName, birthday);
            employee.setGender(gender);
            employee.setJob(job);
            Employee emp = employeeRepository.save(employee);
            generateId = emp.getId().toString();
            success = "true";
		} 
        
        HashMap<String, String> map = new HashMap<>();
        map.put("id", generateId);
        map.put("success", success);
        
        return map;
    }
	
	@PostMapping("/worked_hours")
	HashMap<String, String> addEmployeeWorkedHours(@RequestBody JSONObject inputJsonObj) throws URISyntaxException, ParseException {
		String worked_hours = (String) inputJsonObj.get("worked_hours");
		String date = (String) inputJsonObj.get("worked_date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date worked_date = sdf.parse(date);
	    String employeeId = (String) inputJsonObj.get("employee_id");
	    
	    Employee employee = employeeRepository.findById(Long.parseLong(employeeId)).orElseThrow( () -> new JobNotFoundException(Long.parseLong(employeeId)));;
	    EmployeeWorkedHours employeeWorked = new EmployeeWorkedHours(Double.parseDouble(worked_hours), worked_date);
	    employeeWorked.setEmploye(employee);
	    employeeWorked.setWorkedHours(Double.parseDouble(worked_hours));
	    employeeWorked.setWorkedDate(worked_date);
	    
	    LocalDate localDate =  LocalDate.now();
	    Date dateNow = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    String generateId = null;
        String success = "false";
	    if (Double.parseDouble(worked_hours) < 20 && worked_date.compareTo(dateNow) <= 0) {
	    	EmployeeWorkedHours ewh =  employeeWorkedHoursRepository.save(employeeWorked);
	    	generateId = ewh.getId().toString();
	    	success = "true";
		}
	    HashMap<String, String> map = new HashMap<>();
        map.put("id", generateId);
        map.put("success", success);
        
        return map;
	}
	
	@PostMapping("/get_employees")
	List<Employee> getEmployes(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
		String jobId = (String) inputJsonObj.get("job_id");
		Job job = jobRepository.findById(Long.parseLong(jobId)).orElseThrow( () -> new JobNotFoundException(Long.parseLong(jobId)));;
		return employeeRepository.findByJob(job);
	}
	
	@PostMapping("/total_worked_hours")
	boolean getTotalWorkedHours(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
		String startDate = (String) inputJsonObj.get("start_date");
	    String endDate = (String) inputJsonObj.get("end_date");
	   return true; 
	}
	
	@PostMapping("/total_payment")
	boolean getTotalPayment(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
		String employeeId = (String) inputJsonObj.get("employee_id");
		String startDate = (String) inputJsonObj.get("start_date");
	    String endDate = (String) inputJsonObj.get("end_date");
	   return true; 
	}
	
}
