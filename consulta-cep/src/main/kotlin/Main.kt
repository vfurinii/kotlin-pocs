import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*


// Função em kotlin para consultar o CEP usando a API Viacep
@JsonIgnoreProperties(ignoreUnknown = true)
data class Endereco(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val ibge: String?
)

fun consultaCEP(cep: String): Endereco? {
    val client = OkHttpClient()
    val url = "https://viacep.com.br/ws/$cep/json/"

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            println("Erro ao consultar o CEP: ${response.code}")
            return null
        }

        val body = response.body?.string()
        val mapper = jacksonObjectMapper()
        return mapper.readValue(body!!)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    print("Digite o CEP: ")
    val cep = scanner.nextLine().replace("-", "").trim()

    val endereco = consultaCEP(cep)

    if (endereco != null && endereco.cep != null) {
        println("Endereço encontrado:")
        println("Logradouro: ${endereco.logradouro}")
        println("Bairro: ${endereco.bairro}")
        println("Cidade: ${endereco.localidade} - ${endereco.uf}")
        println("IBGE: ${endereco.ibge}")
    } else {
        println("CEP não encontrado ou inválido.")
    }
}
