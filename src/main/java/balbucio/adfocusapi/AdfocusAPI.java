package balbucio.adfocusapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AdfocusAPI {

    private String key;
    private String customDomain;
    private boolean useCustomDomain = false;
    private String id = null;

    /**
     * Crie uma instância do Adfoc.us API apenas copiando o URL da página https://adfoc.us/tools/api
     * @param urlKey URL com a Key
     */
    public AdfocusAPI(URL urlKey){
        String[] parameters = urlKey.getQuery().split("&");
        for(String parameter : parameters){
            String[] p = parameter.split("=");
            if(p[0].equalsIgnoreCase("key")){
                this.key = p[1];
            }
        }
    }

    /**
     * Crie uma instância do Adfoc.us API com a Key separada do URL.
     * Para fazer isto entre em https://adfoc.us/tools/api e copie apenas APENAS a Key
     * @param key
     */
    public AdfocusAPI(String key){
        this.key = key;
    }

    /**
     * Para usar a ferramenta de encurtamento de site, você precisa do ID, você pode encontrá-lo em
     * https://adfoc.us/tools/site-links, lembre-se de copiar apenas o ID!
     * @param id
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * Copie o seu ID do Adfoc.us API apenas copiando o URL da página https://adfoc.us/tools/site-links
     * Isso te permitirá usar as ferramentas de encurtamento de site;
     * @param urlID URL com o ID
     */
    public void setId(URL urlID){
        String[] parameters = urlID.getQuery().split("&");
        for(String parameter : parameters){
            String[] p = parameter.split("=");
            if(p[0].equalsIgnoreCase("id")){
                this.id = p[1];
            }
        }
    }

    /**
     * Se você usa domínios personalizados no Adfoc.us, você pode usar essa opção para que API já gere os links
     * com o URL personalizado. Por exemplo, se tradicionalmente o link retornaria https://adfoc.us/%id% agora ele
     * passará a retornar https://yourdomain.com/%id% (troque o yourdomain.com pelo seu domínio).
     * @param customDomain Seu domínio COM HTTP/HTTPS:// (ex.: https://links.balbucio.xyz)
     */
    public void setCustomDomain(String customDomain){
        if(!customDomain.contains("https://") && !customDomain.contains("http://")){
            throw new IllegalArgumentException("O Url não contém o protocolo!");
        }
        this.customDomain = customDomain;
        this.useCustomDomain = true;
    }

    /**
     * Crie links encurtados usando esse método, lembre-se o link que você for encurtar deve conter o HTTP/HTTPS://
     * Caso não o coloque será lançado o IllegalArgumentException.
     * @param url Link a ser encurtado (com o HTTP/HTTPS://)
     * @return Link encurtado (e com as preferência prontas)
     */
    public String shortenLink(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            throw new IllegalArgumentException("O Url não contém o protocolo!");
        }
        try {
            URL request = new URL("http://adfoc.us/api/?key="+key+"&url="+url);
            URLConnection rc = request.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(rc.getInputStream()));
            String urlresponse = in.readLine();
            in.close();

            if(urlresponse.equalsIgnoreCase("0")){
                throw new InvalidShortenException(url, "O URL não pode ser encurtado. (request return 0)");
            }

            if(useCustomDomain){
                urlresponse = urlresponse.replace("http://adfoc.us", customDomain);
            }
            return urlresponse;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Crie links encurtados usando esse método, lembre-se o link que você for encurtar deve conter o HTTP/HTTPS://
     * Caso não o coloque será lançado o IllegalArgumentException.
     * @param url Link a ser encurtado (com o HTTP/HTTPS://)
     * @return Retorna um AdfocusShortenLink onde você pode ter mais detalhes sobre o link encurtado
     */
    public AdfocusShortenLink shortenLinkWithAllInformations(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            throw new IllegalArgumentException("O Url não contém o protocolo!");
        }
        try {
            URL request = new URL("http://adfoc.us/api/?key="+key+"&url="+url);
            URLConnection rc = request.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(rc.getInputStream()));
            String urlresponse = in.readLine();
            in.close();

            if(urlresponse.equalsIgnoreCase("0")){
                throw new InvalidShortenException(url, "O URL não pode ser encurtado. (request return 0)");
            }

            String finalurl = urlresponse;
            if(useCustomDomain){
                finalurl = urlresponse.replace("http://adfoc.us", customDomain);
            }
            return new AdfocusShortenLink(finalurl, urlresponse, Integer.getInteger(urlresponse.replace("http://adfoc.us/", "")));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Use esse método para criar um Link de encurtamento de Site rápido.
     * Essa ferramenta não é igual a de encurtamento de links, entre em https://adfoc.us/tools/site-links para saber mais
     * @param url
     * @return
     */
    public String shortenSite(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            throw new IllegalArgumentException("O Url não contém o protocolo!");
        }
        if(id == null){
            throw new IllegalArgumentException("Você não setou o seu ID!");
        }
        return "http://adfoc.us/serve/sitelinks/?id="+id+"&url="+url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCustomDomain() {
        return customDomain;
    }

    public boolean isCustomDomain() {
        return useCustomDomain;
    }

    public void useCustomDomain(boolean useCustomDomain) {
        this.useCustomDomain = useCustomDomain;
    }
}
