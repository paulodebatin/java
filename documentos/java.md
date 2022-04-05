# Gerar uma chave Random
System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-"  + UUID.randomUUID() );

# Cache em API Rest no lado do client
https://www.algaworks.com/aulas/827/adicionando-cache (4:41)

# Multi tentant spring boot jpa
https://tech.asimio.net/2017/01/17/Multitenant-applications-using-Spring-Boot-JPA-Hibernate-and-Postgres.html


# Start application Spring Boot command line
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081

# Formatar datas
DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

# Monitorar aplicações Java
https://github.com/javamelody/javamelody/wiki/UserGuide
https://www.baeldung.com/java-flight-recorder-monitoring
https://docs.oracle.com/javase/6/docs/technotes/guides/management/jconsole.html
https://visualvm.github.io/

# Spring Null-Safety Annotations
https://www.baeldung.com/spring-null-safety-annotations

public class Teste {
	
	private String nome;
	
	public void setaNome(@NonNull String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static void main(String[] args) {
		Teste teste = new Teste();
		//teste.setaNome("Paulo");
		teste.setaNome(null);
	}
}