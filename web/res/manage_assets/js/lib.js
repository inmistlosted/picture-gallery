function closeBackConn()
{
    let backConn = document.getElementById("backConnection");
    backConn.style.display = "none";
 
}

function openBackConn()
{
    let backConn = document.getElementById("backConnection");
    backConn.style.display = "block";
    backConn.style.animation = "fadeout 1s";

}

function point(id)
{
    let word = document.getElementById(id);
    word.className = "pointer";
}

function unpoint(id)
{
    let word = document.getElementById(id);
    word.className = "unpointer";
}

function pointPriceList()
{
    let pricelist = document.getElementById("priceList");
    pricelist.className = "pricePointer";
}

function unpointPriceList()
{
    let pricelist = document.getElementById("priceList");
    pricelist.className = "priceList";
}

function pointCallLink()
{
    let pricelist = document.getElementById("callLink");
    pricelist.className = "callLinkPointer";
}

function unpointCallLink()
{
    let pricelist = document.getElementById("callLink");
    pricelist.className = "callLink";
}

function pointMenu(id)
{
    let menubar = document.getElementById(id);
    menubar.style.color = "dodgerblue";
}

function unpointMenu(id)
{
    let menubar = document.getElementById(id);
    menubar.style.color = "black";
}

function pointNums(id)
{
    let num = document.getElementById(id);
    num.className = "bluenum";
}

function unpointNums(id)
{
    let num = document.getElementById(id);
    num.className = "normal";
}

function pointCart(id) {
    let cart = document.getElementById(id);
    cart.className = 'cartFotoPointer';
}

function unpointCart(id) {
    let cart = document.getElementById(id);
    cart.className = 'cartFoto';
}


let name = "";
let help_name = "";
function errorName(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === '')
    {
        pole.value = "Это поле обязaтельно к заполнению";
        pole.className = 'error';
    }
    else if (poleVal.match(/\d/) !== null)
    {
        help_name = poleVal;
        pole.value = "Это поле не должно содержать чисел";
        pole.className = 'error';
    }
    else
    {
        name = poleVal;
    }

}

function removeErrorName(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === 'Это поле обязaтельно к заполнению')
    {
        pole.value = "";
        pole.className = 'data';
    }
    else
    {
        pole.value = name;
        pole.className = 'data';
    }
}

let tel = "";
let help_tel = "";
function errorTel(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === '')
    {
        pole.value = "Это поле обязaтельно к заполнению";
        pole.className = 'error';
    }
    else if (poleVal.match(/[^0-9]/) !== null)
    {
        help_tel = poleVal;
        pole.value = "Это поле не должно содержать букв";
        pole.className = 'error';
    }
    else
    {
        tel = poleVal;
    }

}

function removeErrorTel(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === 'Это поле обязaтельно к заполнению')
    {
        pole.value = "";
        pole.className = 'data';
    }
    else
    {
        pole.value = tel;
        pole.className = 'data';
    }
}

let email = "";
let help_email = "";
function errorEmail(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === '')
    {
        pole.value = "Это поле обязaтельно к заполнению";
        pole.className = 'error';
    }
    else if (poleVal.match(/@/) === null)
    {
        help_email = poleVal;
        pole.value = "Это поле должно содержать @";
        pole.className = 'error';
    }
    else if(poleVal.match(/[а-я]/) !== null)
    {
        help_email = poleVal;
        pole.value = "Это поле не должно содержать кириллицу";
        pole.className = 'error';
    }
    else
    {
        email = poleVal;
    }

}

function removeErrorEmail(id)
{
    let pole = document.getElementById(id);
    let poleVal = pole.value;
    if(poleVal === 'Это поле обязaтельно к заполнению')
    {
        pole.value = "";
        pole.className = 'data';
    }
    else
    {
        pole.value = email;
        pole.className = 'data';
    }
}

let messages = [];
function sendMessage()
{
    let message = document.getElementById('message').value;
    let pole1 = document.getElementById('name');
    let pole2 = document.getElementById('tel');
    let pole3 = document.getElementById('email');
    if(name !== '' && tel !== '' && email !== '')
    {
        let person = {
            name,
            tel,
            email,
            message
        }
        messages = [...messages, person];
        alert('Сообщение отправлено');
    }
    else
    {

        if(name === '')
        {
            pole1.value = "Это поле обязaтельно к заполнению";
            pole1.className = 'error';
        }
        if(tel === '')
        {
            pole2.value = "Это поле обязaтельно к заполнению";
            pole2.className = 'error';
        }
        if(email === '')
        {
            pole3.value = "Это поле обязaтельно к заполнению";
            pole3.className = 'error';
        }
        alert('Форма заполнена неправильно');
    }
}


