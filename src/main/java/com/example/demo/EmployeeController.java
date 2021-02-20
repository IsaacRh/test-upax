package com.example.demo;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	private final GenderRepository genderRepository;
	private final JobRepository jobRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository, GenderRepository genderRepository,
			JobRepository jobRepository) {
       this.employeeRepository = employeeRepository;
       this.genderRepository = genderRepository;
       this.jobRepository = jobRepository;
       
   }
	
	
	@PostMapping("/employee")
    boolean addEmployee(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
        String name = (String) inputJsonObj.get("name");
        String lastName = (String) inputJsonObj.get("last_name");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = (String) inputJsonObj.get("birthday");
		
        String genderId = (String) inputJsonObj.get("gender_id");
        String jobId = (String) inputJsonObj.get("job_id");
        
        System.out.println(genderId);
        System.out.println(jobId);
        Gender gender = genderRepository.findById(Long.parseLong(genderId)).orElseThrow( () -> new GenderNotFoundException(Long.parseLong(genderId)));;
        Job job = jobRepository.findById(Long.parseLong(jobId)).orElseThrow( () -> new JobNotFoundException(Long.parseLong(genderId)));;
       
        Employee employee = new Employee(name, lastName, birthday);
        employee.setGender(gender);
        employee.setJob(job);
        employeeRepository.save(employee);
        
        return true;
        //Resource<Student> resource = assembler.toResource(repository.save(newStudent));
        //return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }
	
	@PostMapping("/worked_hours")
	boolean addEmployeeWorkedHours(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
		String worked_hours = (String) inputJsonObj.get("worked_hours");
	    String worked_date = (String) inputJsonObj.get("worked_date");
	    String employeeId = (String) inputJsonObj.get("employee_id");
	    Employee employee = employeeRepository.findById(Long.parseLong(employeeId)).orElseThrow( () -> new JobNotFoundException(Long.parseLong(employeeId)));;
	    EmployeeWorkedHours employeeWorked = new EmployeeWorkedHours(Double.parseDouble(worked_hours), worked_date);
	    employeeWorked.setEmploye(null);
		return true;
	}
	
	@PostMapping("/get_employees")
	boolean getEmployes(@RequestBody JSONObject inputJsonObj) throws URISyntaxException {
		String jobId = (String) inputJsonObj.get("job_id");
	   return true; 
	}
	
}
