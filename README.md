# AdfocusAPI
This is a simple Java API designed to handle Links using the Adfoc.us platform.
Esta é uma API simples em Java, feita para manipular Links usando a plataforma Adfoc.us.

## Adicionando a dependência
Gradle:
```Gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	dependencies {
	        implementation 'com.github.SrBalbucio:AdfocusAPI:1.0'
	}
```
Maven:
```Maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	<dependency>
	    <groupId>com.github.SrBalbucio</groupId>
	    <artifactId>AdfocusAPI</artifactId>
	    <version>1.0</version>
	</dependency>
```

## Primeiros passos
Para começar a criar links encurtados é necessário criar uma instância do AdfocusAPI.
Há duas maneiras de criar esse objeto, como mostrado abaixo:
```Java
// Você pode criar apenas usando a key da sua conta
AdfocusAPI api = new AdfocusAPI(<key>);
// Ou pode também copiar a URL todo da sua conta
AdfocusAPI api = new AdfocusAPI(new URL(<url>));
```
(Se você não sabe onde encontrar essa key, entre neste link https://adfoc.us/tools/api)

## Algumas configurações adicionais
A API suporta algumas preferências da sua conta, você pode configurá-las usando os métodos abaixo.

### Domínio personalizado
Se você usa domínios personalizados no Adfoc.us você pode optar por criar os links encurtados já com o domínio.
```Java
// Use este método para setar seu domínio personalizado e lembre-se de usar o HTTP/HTTPS://
api.setCustomDomain(<url>);
// Exemplo
api.setCustomDomain("https://plugins.balbucio.xyz");
```
### ID da conta
Se você pretende usar a ferramenta de encurtamento de Site, você precisa setar previamente o ID da sua conta, que pode ser encontrado em: https://adfoc.us/tools/site-links
<br> Novamente, há duas maneiras de você setar o ID, como mostrado abaixo:
```Java
// Use esse método para setar o ID separado
api.setId(<id>);
// ou use esse método para setar o ID usando a URL completa
api.setId(new URL(<url>));
```

## Criando links encurtados
Agora que você criou e configurou a API, podemos começar a criar links encurtados!

### Retornando apenas o link
Se você quer apenas o Link encurtado e pronto, você pode usar o método abaixo:
```Java
// Não se esqueça de por HTTP/HTTPS:// no link a ser encurtado
String link = api.shortenLink(<url>);
```

### Retornando informações completas
Se você quer mais informações sobre os links encurtados, como a data que foram criados, o link original e afins, você pode usar o método abaixo:
```Java
// Não se esqueça de por HTTP/HTTPS:// no link a ser encurtado
AdfocusShortenLink link = api.shortenLinkWithAllInformations(<url>);
link.getFinalurl(); // Retorna o Link finalizado
link.getOriginalurl(); // Retorna o Link original criado pelo Adfoc.us
link.getId(); // Retorna o ID do Link encurtado (o código no final do link)
link.getDate(); // Retorna a data de criação do link
link.getTime(); // Retorna o long time de criação do link
```

## Criando um link de encurtamento de site
Este método cria um link de encurtamento de site, como pode ser visto em: https://adfoc.us/tools/site-links
```Java
// Não se esqueça de por HTTP/HTTPS:// no link a ser encurtado
String link = api.shortenSite(<url>);
```
## Avisos
Não se esqueça de sempre por HTTP/HTTPS:// nos URLs a serem usados, se você não fizer isso será lançado um IllegalArgumentException no console.
Caso o adfoc.us encontre um erro ao processar o seu link encurtado será retornado um InvalidShortenException, com o link recusado e uma mensagem de erro genérica.
Se o seu link personalizado com domínio saiu errado, considere verificar o seu método setCustomDomain.
