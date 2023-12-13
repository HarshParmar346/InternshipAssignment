package assignment.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.web.servlet.server.Encoding.Type;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import assignment.Model.Customer;
import assignment.Model.CustomerList;
import assignment.Model.LoginBody;

@Service
public class CustomerService {

	public String login(LoginBody user) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<LoginBody> entity = new HttpEntity<LoginBody>(user, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp", HttpMethod.POST, entity,
				String.class);

		return responseEntity.getBody().replace("\"", "").replace("access_token", "").replace("{", "").replace("}", "")
				.replace(":", "").replace(" ", "").replace("\n", "");
	}

	public void createUser(String accessCode, Customer customer) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("Authorization", "Bearer " + accessCode);

		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);

		System.out.println(entity.getBody());

		ResponseEntity<String> response = restTemplate.exchange(
				"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create", HttpMethod.POST, entity,
				String.class);
		// TODO Auto-generated method stub

		System.out.println(response.getStatusCode() == HttpStatus.CREATED);

	}

	public List<CustomerList> getList(String accessCode) {
		// TODO Auto-generated method stub

		if (accessCode.equals("")) {
			return Collections.emptyList();
		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("Authorization", "Bearer " + accessCode);

		Gson gson = new Gson();

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list", HttpMethod.GET,
				entity, String.class);

		List<CustomerList> list = gson.fromJson(response.getBody(), new TypeToken<List<CustomerList>>() {
		}.getType());
		System.out.println(list);
		return list;
	}

	public void update(String accessCode, Customer customer, String uuid) {
		// TODO Auto-generated method stub
		if (accessCode == null) {

		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("Authorization", "Bearer " + accessCode);

		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);

		System.out.println(uuid);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid=" + uuid,
				HttpMethod.POST, entity, String.class);
		// TODO Auto-generated method stub

		System.out.println(response.getStatusCode() == HttpStatusCode.valueOf(200));

	}

	public void delete(String accessCode, String uuid) {
		// TODO Auto-generated method stub

		if (accessCode == null) {

		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("Authorization", "Bearer " + accessCode);

		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid=" + uuid,
				HttpMethod.POST, entity, String.class);
		
		System.out.println(response.getStatusCode() == HttpStatusCode.valueOf(200));

	}

}
