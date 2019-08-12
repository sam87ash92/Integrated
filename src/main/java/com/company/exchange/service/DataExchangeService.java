package com.company.exchange.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.company.exchange.error.ErrorCode;
import com.company.exchange.model.Department;
import com.company.exchange.model.Employee;
import com.company.exchange.model.Monitor;
import com.company.exchange.repository.DepartmentRepository;
import com.company.exchange.repository.EmployeeRepository;
import com.company.exchange.repository.MonitorRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

@Service
public class DataExchangeService {

	private Logger LOG = LoggerFactory.getLogger(DataExchangeService.class);

	private RestTemplate template = new RestTemplate();

	@Autowired
	private MonitorRepository monitorRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public void getDataFromExchange() {

		String url = "https://api.mockaroo.com/api/85883510?count=5&key=61068170";

		ResponseEntity<String> response = template.getForEntity(url, String.class);

		List<Employee> employees = new ArrayList<>();

		if (response.hasBody() && !response.getStatusCode().isError()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode node = mapper.readTree(response.getBody());
				int i = 0;
				while (node.has(i)) {
					Employee employee = new Employee(node.get(i).findValue("id").asInt(),
							node.get(i).findValue("first_name").asText(), node.get(i).findValue("last_name").asText(),
							node.get(i).findValue("email").asText(), node.get(i).findValue("gender").asText(),
							node.get(i).findValue("department").asText());
					++i;
					employees.add(employee);
					employeeRepository.save(employee);
				}
				monitorRepository.save(new Monitor(employees.size(), "getDataFromExchange",
						Integer.toString(response.getStatusCodeValue())));
			} catch (Exception exception) {
				monitorRepository.save(
						new Monitor(employees.size(), "getDataFromExchange", ErrorCode.EMPLOYEE_DATA_READ_ERROR_CODE));
			}
		} else {
			LOG.error("Empty response of error status from Pull data operation", response);
		}
	}

	public void postDataToExchange() {

		List<Department> departments = new ArrayList<>();
		departmentRepository.findAll().forEach(departments::add);

		GsonBuilder builder = new GsonBuilder();
		String jsonObject = builder.setPrettyPrinting().create().toJson(departments);

		try {
			// Random URL which would result in 500 response status
			String url = "https://api.mockaroo.com/api/85883510?count='" + departments.size() + "'&key=department";

			if (!departments.isEmpty()) {
				LOG.info(departments.size() + " records have been picked from DB");
				HttpHeaders header = new HttpHeaders();
				header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

				ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, new HttpEntity<String>(header),
						String.class, jsonObject);

				monitorRepository.save(new Monitor(departments.size(), "postDataToExchange",
						Integer.toString(response.getStatusCodeValue())));
				LOG.info(departments.size() + " records have been posted to external site");
			} else {
				LOG.info("No records found in DB to POST");
			}
		} catch (HttpServerErrorException e) {
			monitorRepository.save(new Monitor(departments.size(), "postDataToExchange",
					ErrorCode.POST_DEPARTMENT_INTERNAL_SERVER_ERROR));
			LOG.error("Not able to send data over http");
		}
	}
}
