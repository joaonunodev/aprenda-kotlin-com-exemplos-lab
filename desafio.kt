enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val email: String)

var listaUsuarios = arrayListOf<Usuario>()
var nomeUsuario = ""
var emailUsuario = ""

data class ConteudoEducacional(var nome: String, var duracao: Int = 60)
var nomeConteudoEducacional = ""
var duracaoConteudoEducacional = 60
var listaDeConteudoEducacional = arrayListOf<ConteudoEducacional>()

data class Formacao(val nome: String, val nivel: Nivel , var conteudos: ArrayList<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        //TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        inscritos.add(usuario)
        println("O usuário $usuario foi inscrito com sucesso em $nome")
        println("Lista de usuários inscritos em $nome: ")
        for (inscrito in 0 until inscritos.size) {
            println(inscritos[inscrito].nome)
        }
    }
}

var listaFormacao = arrayListOf<Formacao>()
var nomeFormacao = ""
var nivelFormacao: Nivel = Nivel.BASICO
var listaConteudosFormacao = arrayListOf<ConteudoEducacional>()


fun criarUsuario(): Usuario {
    println("Digite o Nome do Usuário a inscrever:")
    nomeUsuario = readLine().toString()
    println("Digite o email do Usuário a inscrever:")
    emailUsuario = readLine().toString()

    var usuario : Usuario
    usuario = Usuario(nomeUsuario, emailUsuario)
    return usuario
}

fun criarFormacao() : Formacao {
    println("** CRIAR NOVA FORMAÇÃO **")

    println("Digite o nome da nova Formação:")
    nomeFormacao = readLine().toString()

    println("Digite o nível da nova Formação: Digite 1 para Básico, 2 para Intermediário, 3 para Avançado")
    var nivel = readLine()!!.toInt()
    var nivelFormacao : Nivel = when (nivel) {
        2 -> Nivel.INTERMEDIARIO
        3 -> Nivel.AVANCADO
        else -> Nivel.BASICO
    }

    println("Inserção de Conteúdos Educacionais")
    do {
        println("Indique o nome do Conteúdo Educacional:")
        nomeConteudoEducacional = readLine().toString()
        println("Indique a duração do Conteúdo Educacional:")
        duracaoConteudoEducacional = readLine()!!.toInt()
        var novoConteudoEducacional = ConteudoEducacional(nomeConteudoEducacional, duracaoConteudoEducacional)
        listaDeConteudoEducacional.add(novoConteudoEducacional)

        println("Pretende adicionar mais algum Conteúdo Educacional? S para sim, outra tecla para não")
        var resposta = readLine().toString()
    } while (resposta == "S")

    val novaFormacao = Formacao(nomeFormacao, nivelFormacao, listaDeConteudoEducacional)

    return novaFormacao
}


fun main() {

    // Cadastramento de Usuários

    do {
        println("Pretende inscrever novo usuário? digite S para sim / N para não")
        var respostaInscricao = readLine()!!.toString()

        if (respostaInscricao != "S") {
            println("Não foi selecionado o cadastramento de mais nenhum usuário!")
        } else {
            var novoUsuario = criarUsuario()
            listaUsuarios.add(novoUsuario)
            println(listaUsuarios)
            println("Usuário cadastrado com sucesso!")
        }
    } while(respostaInscricao == "S")

    // Inserção de Formações

    val conteudoEducacionalKotlin01 = ConteudoEducacional("Princípios de Agilidade e Desenvolvimento de software", 37)
    val conteudoEducacionalKotlin02 = ConteudoEducacional("Dominando a Linguagem de Programação kotlin", 41)
    val conteudoEducacionalKotlin03 = ConteudoEducacional("Refinando a sua técnica com Desafios de Código Kotlin", 30)
    val conteudoEducacionalKotlin04 = ConteudoEducacional("Fortalecendo o seu perfil Profissional", 25)

    val listaConteudoEducacionalKotlin = arrayListOf<ConteudoEducacional>(
        conteudoEducacionalKotlin01,
        conteudoEducacionalKotlin02,
        conteudoEducacionalKotlin03,
        conteudoEducacionalKotlin04)

    val formacaoKotlin = Formacao("Kotlin Experience", Nivel.BASICO, listaConteudoEducacionalKotlin)

    var listaDeFormacoes = arrayListOf<Formacao>()

    listaDeFormacoes.add(formacaoKotlin)

    println("Formações existentes:")
    // println("Lista de Formações: $listaDeFormacoes")

    for(i in 0 until listaDeFormacoes.size) {
        println(listaDeFormacoes[i].nome)
    }

    // Adicionar novas formações

    do {
        println("Pretende adicionar nova formação? digite S para sim / N para não")
        var respostaInscricao = readLine()!!.toString()

        if (respostaInscricao != "S") {
            println("Não foi selecionado o cadastramento de mais nenhuma formação!")
        } else {
            var novaFormacao = criarFormacao()
            listaDeFormacoes.add(novaFormacao)

            for(i in 0 until listaDeFormacoes.size) {
                println(listaDeFormacoes[i].nome)
            }
            println("Formação adicionada com sucesso!")
        }
    } while(respostaInscricao == "S")


    // Inscrição de Usuários nas Formações

    do {
        println("Pretende inscrever Usuário em uma Formação? digite S para sim / N para não")
        var respostaInscricao = readLine()!!.toString()

        if (respostaInscricao != "S") {
            println("Não foi selecionado a inscrição de Usuário em Formação!")
        } else {
            println("Selecione o número do usuário a inscrever em uma Formação:")
            var k = 1
            for (usuario in 0 until listaUsuarios.size) {
                var nome = listaUsuarios[usuario].nome
                println("Para selecionar o usuário $nome selecione $k")
                k++
            }
            var numeroUsuarioSelecionado = readLine()!!.toInt()
            var usuarioSelecionado = listaUsuarios[numeroUsuarioSelecionado-1]

            println("Selecione o número da Formação para inscrever o usuário: ")
            k = 1
            for (formacao in 0 until listaDeFormacoes.size) {
                var nome = listaDeFormacoes[formacao].nome
                println("Para selecionar a Formação $nome selecione $k")
                k++
            }
            var numeroFormacaoSelecionada = readLine()!!.toInt()
            var formacaoSelecionada = listaDeFormacoes[numeroFormacaoSelecionada-1]

            formacaoSelecionada.matricular(usuarioSelecionado)
        }
    } while(respostaInscricao == "S")

    println("  ** Um bom dia para você! **  ")
    println("  ** Fim do Programa **  ")
}
