package gob.pe.essalud.apireporseg.util;

import gob.pe.essalud.apireporseg.planilla.model.Archivo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "sftp")
public class FileSystemStorageService {

	private String server;
	private int port;
	private String username;
	private String password;
	private String path;


	private Path uploadLocation;
	private final String TIPO_SUBSIDIO="SEV";
	private final String FILE_SAVE_DATE_PATTERN = "dd-MM-yyyy_HHmmss";


	private static List<Archivo> ltsArchivos;
	static {

		ltsArchivos=new ArrayList<>();
	}

	@PostConstruct
	public void init() {

	}

	public Archivo store(MultipartFile file,String tpDocTitu,String docTitu,String ruc,Integer i) throws IOException {
		Util util = new Util();
		Date date = new Date();
		Archivo archivo= new Archivo();
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("File: "+filename);
		archivo.setNombreArchivo(filename.toString());
		// archivo.setNombreArchivoNew(util.cambioNombrePDF(docTitu,ruc,i));

		String filePath = "/" + tpDocTitu + "_" +
				docTitu + "/";

		uploadLocation = Paths.get(path+filePath);
		//filename = util.cambioNombrePDF(docTitu,ruc,i);
		filename = TIPO_SUBSIDIO +i+
				"_" +
				new SimpleDateFormat(FILE_SAVE_DATE_PATTERN).format(date) +
				"." +
				FilenameUtils.getExtension(file.getOriginalFilename());

		archivo.setNombreArchivoNew(filename);

		Files.createDirectories(uploadLocation);

		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + filename);
			}

			// This is a security check
			if (filename.contains("..")) {
				throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
			}

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.uploadLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
//				sftpClient.upload(inputStream, uploadLocation.normalize().toString(),filename);
			}
			String url=uploadLocation.resolve(filename).normalize().toString();
			System.out.println(url);
			archivo.setUrlDirectorio(url);

			//ltsArchivos.add(archivo);
			return archivo;
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + filename, e);
		}
	}

	public List<Archivo> listSourceFiles() throws IOException {
		return ltsArchivos;
	}

	public Path getUploadLocation() {
		return uploadLocation;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

