package cn.netkiller.wallet.restful;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ipfs")
public class InfuraIpfsRestController {

	private static final Logger logger = LoggerFactory.getLogger(InfuraIpfsRestController.class);
	private final String url = "https://ipfs.infura.io:5001/api/v0";

	public InfuraIpfsRestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/cat/{hash}")
	public String add(@PathVariable String hash) {
		RestTemplate restTemplate = new RestTemplate();
		String text = restTemplate.getForObject(String.format("%s/cat?arg=%s", this.url, hash), String.class);
		logger.info(text);
		return text;
	}

	@PostMapping("/add")
	public String fileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

		String name = multipartFile.getOriginalFilename();
		System.out.println("File name: " + name);
		// todo save to a file via multipartFile.getInputStream()
		byte[] bytes = multipartFile.getBytes();
		System.out.println("File uploaded content:\n" + new String(bytes));

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", multipartFile.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("https://ipfs.infura.io:5001/api/v0/add?pin=false", HttpMethod.POST, requestEntity, String.class);

		System.out.println("response status: " + response.getStatusCode());
		System.out.println("response body: " + response.getBody());

		return response.getBody();
	}

	// public static Resource getUserFileResource() throws IOException {
	// // todo replace tempFile with a real file
	// Path tempFile = Files.createTempFile("upload-test-file", ".txt");
	// Files.write(tempFile, "some test content...\nline1\nline2".getBytes());
	// System.out.println("uploading: " + tempFile);
	// File file = tempFile.toFile();
	// // to upload in-memory bytes use ByteArrayResource instead
	// return new FileSystemResource(file);
	// }
}
