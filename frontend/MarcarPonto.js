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
    const hora =zeroFill(now.getHours()) + ':' + zeroFill(now.getMinutes());

    let mesesString;
    switch (now.getMonth()+ 1) {
        case 1: {mesesString = "Janeiro"; break;}
        case 2: {mesesString = "Fevereiro"; break;}
        case 3: {mesesString = "Março"; break;}
        case 4: {mesesString = "Abril"; break;}
        case 5: {mesesString = "Maio"; break;}
        case 6: {mesesString = "Junho"; break;}
        case 7: {mesesString = "Julho"; break;}
        case 8: {mesesString = "Agosto"; break;}
        case 9: {mesesString = "Setembro"; break;}
        case 10: {mesesString = "Outubro"; break;}
        case 11: {mesesString = "Novembro"; break;}
        case 12: {mesesString = "Dezembro"; break;}
    }

    // Pegando Dia, Mes e Ano
    const data = zeroFill(now.getDay()) +' de '+ mesesString +' de '+ now.getFullYear();

    // Exibe na tela usando a div#data-hora
    document.getElementById('baterPonto-hora').innerHTML = hora;

    // Exibe na tela usando o id = baterPonto-data
    document.getElementById('baterPonto-data').innerHTML = data;
}, 1000);
