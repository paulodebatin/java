# elasticsearch-java

Projeto maven, de aplicação Java console que demonstra como gravar/alterar, consultar documentos no ElasticSearch.

### Para testar
Inicie um servidor ElasticSearch. Recomendo um docker.
Abra o projeto em um editor (Eclipse, Intelij, ...) e execute a classe Testes.java. Acerte os dados para conexão neste mesmo arquivo.

Pode acessar via navegador: http://localhost:9200/produtos/_doc/1

### Tecnologias utilizadas:
* Java
* ElasticSearch
* Dependência maven para o client: elasticsearch-rest-client, elasticsearch-rest-high-level-client