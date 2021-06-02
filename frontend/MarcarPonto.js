const zeroFill = n => {
    return ('0' + n).slice(-2);
}

// Cria intervalo
const interval = setInterval(() => {
    // Pega o horário atual
    const now = new Date();

    // Formata a data conforme dd/mm/aaaa hh:ii:ss
   // const dataHora = zeroFill(now.getUTCDate()) + '/' + zeroFill((now.getMonth() + 1)) + '/' + now.getFullYear() + ' ' + zeroFill(now.getHours()) + ':' + zeroFill(now.getMinutes()) + ':' + zeroFill(now.getSeconds());

   // Pegando Hora e Minuto
    const hora = zeroFill(now.getHours()) + ':' + zeroFill(now.getMinutes());

    // Pegando Dia, Mes e Ano
    const data = zeroFill(now.getDay()) +' de '+ zeroFill(now.getMonth() + 1)+' de '+ zeroFill(now.getFullYear());

    // Exibe na tela usando a div#data-hora
    document.getElementById('baterPonto-hora').innerHTML = hora;

    // Exibe na tela usando o id = baterPonto-data
    document.getElementById('baterPonto-data').innerHTML = data;
}, 1000);
