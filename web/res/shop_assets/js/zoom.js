let price;
let bookPrice;
let bookName;
let delivery;
let bookPackage;
let quantity;


function selectBook() {
    let bookIndex = document.getElementById('books').selectedIndex;
    let books = document.getElementById('books').options;
    bookName = books[bookIndex].text;
    bookPrice = +books[bookIndex].value;
}

function dynCount() {
    let quan = document.getElementById('quan').value;
    quantity = quan;
    let currPrice = document.getElementById('price');
    currPrice.value = (quan * bookPrice).toString();
    price = +currPrice.value;
}

function countPrice() {
    let dost = document.getElementsByName('dost');
    let pack = document.getElementById('pack');
    for(let i = 0; i < dost.length; i++)
    {
        if(dost[i].checked)
        {
            delivery = dost[i].value;
        }
    }
    if(pack.checked)
    {
        bookPackage = pack.value;
        price += price * 0.2;
    }
    console.log(delivery);
    console.log(bookPackage);

    let result = document.getElementById('endPrice');
    price = Math.round(price*100)/100;
    result.value = price;
}

function getData() {
    let name = document.getElementById('name').value;
    let adress = document.getElementById('adress').value;

    let results = document.getElementById('results');
    let title = document.createElement('h2');
    title.innerHTML = "Ваше замовлення";
    results.appendChild(title);
    let showName = document.createElement('h3');
    showName.innerHTML = `Ім'я замовника: ${name}`;
    results.appendChild(showName);
    let showAdress = document.createElement('h3');
    showAdress.innerHTML = `Адреса замовника: ${adress}`;
    results.appendChild(showAdress);
    let bookN = document.createElement('h3');
    bookN.innerHTML = `Назва книги: ${bookName}`;
    results.appendChild(bookN);
    let bookQ = document.createElement('h3');
    bookQ.innerHTML = `Кількість книг: ${quantity}`;
    results.appendChild(bookQ);
    let del = document.createElement('h3');
    del.innerHTML = `Спосіб доставки: ${delivery}`;
    results.appendChild(del);
    if(bookPackage)
    {
        let bookP = document.createElement('h3');
        bookP.innerHTML = `У святковій упацовці`;
        results.appendChild(bookP);
    }
    let allPrice = document.createElement('h3');
    allPrice.innerHTML = `Загальна вартість: ${price} гривень`;
    results.appendChild(allPrice);

}