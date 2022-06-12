let turnX_bool = true;
let turnY_bool = true;
let ins_size_small = -1;
let ins_size_big = -1;

const sliders = document.querySelectorAll('.sliders input');
sliders.forEach(slider => slider.addEventListener('click', changeImage));
sliders.forEach(slider => slider.addEventListener('mousemove', changeImage));

const compositions = document.querySelectorAll('.composition');
compositions.forEach(composition => composition.addEventListener('click', changeComposition));

const borders = document.querySelectorAll('.brdr_types');
borders.forEach(borderType => borderType.addEventListener('click', changeBorder));

const font = document.querySelectorAll('.font');
font.forEach(fontSt => fontSt.addEventListener('click', changeFont));

const shapes = document.querySelectorAll('.shapes');
shapes.forEach(shape => shape.addEventListener('click', changeShape));

const inscription = document.querySelector('#inscription_small');
inscription.style.display = 'none';

const originalImage  = document.getElementById('originalImage');
originalImage.style.display = 'none';

const all_shapes = document.querySelector('.all_shapes');
all_shapes.style.display = 'none';

function changeImage() {
    let unit = this.dataset.sizing || ``;
    document.documentElement.style.setProperty(`--${this.name}`, this.value + unit);
    this.nextElementSibling.innerHTML = this.value + unit;

    if (this.nextElementSibling.classList == `withoutUnit`) {
        this.nextElementSibling.innerHTML = ``;
    };
}

function changeBorder() {
    document.documentElement.style.setProperty('--border-styles', this.value);
}

function changeComposition() {
    document.documentElement.style.setProperty('--mix-blend-mode', this.value );
}

function changeFont() {
    document.documentElement.style.setProperty('--font', this.value);
}

function changeShape() {
    const shape = this.dataset.shape || "";
    document.documentElement.style.setProperty('--webkit-clip-path', shape);
    document.documentElement.style.setProperty('--clip-path', shape);
    all_shapes.style.display = 'none';
}

function showOriginalImage() {
    const originalImage = document.getElementById('originalImage');
    if (originalImage.style.display == 'none') {
        originalImage.style.display = 'block';
    }
    else {
        originalImage.style.display = 'none';
    }
}

function turnX() {
    const scaleX = document.getElementById('scaleX');
    if (turnX_bool === true) {
        document.documentElement.style.setProperty('--scaleX', -1);
        turnX_bool = false;
        scaleX.value = -1;
    }
    else {
        document.documentElement.style.setProperty('--scaleX', 1);
        turnX_bool = true;
        scaleX.value = 1;
    }
}

function turnY() {
    const scaleY = document.getElementById('scaleY');
    if (turnY_bool === true) {
        document.documentElement.style.setProperty('--scaleY', -1);
        scaleY.value = -1;
        turnY_bool = false;
    }
    else {
        document.documentElement.style.setProperty('--scaleY', 1);
        turnY_bool = true;
        scaleY.value = 1;
    }
}

function chooseShape() {
    const all_shapes = document.querySelector('.all_shapes');
    if (all_shapes.style.display == 'none') {
        all_shapes.style.display = 'block';
    }
    else {
        all_shapes.style.display = 'none';
    }
}

function addInscription() {
    let inscription_value = document.getElementById('inscription_value');
    let inscriptionBig = document.querySelector('#inscription_big');
    let curr1 = '<span class="ins_sm"> '+ inscription_value.value + '</span>';
    let curr2 = '<span class="ins_bg"> '+ inscription_value.value + '</span>';
    inscription.innerHTML += curr1;
    inscriptionBig.innerHTML += curr2;
    inscription.style.display = 'block';
    inscription_value.value = "";
    ins_size_small +=  1;
    ins_size_big +=  1;
}

function removeInscription() {
    let inscript_sm = document.querySelectorAll('.ins_sm');
    let inscript_bg = document.querySelectorAll('.ins_bg');

    for (let i = 0; i < inscript_sm.length; i++) {
        if ( i == ins_size_small) {
            inscript_sm[i].remove();
            ins_size_small -= 1;
        }
    }

    for (let i = 0; i < inscript_bg.length; i++) {
        if ( i == ins_size_big) {
            inscript_bg[i].remove();
            ins_size_big -= 1;
        }
    }
}

function switchInscription() {
    let inscriptionBig = document.querySelector('#inscription_big');

    if (inscription.style.display == 'none') {
        inscription.style.display = 'block';
        inscriptionBig.style.display = 'block';
    }
    else {
        inscription.style.display = 'none';
        inscriptionBig.style.display = 'none';
    }
}

function openFullImage() {
    const fullImg = document.getElementById('full_img');
    fullImg.style.display = "block";

    fullImg.addEventListener("click", function() {
        fullImg.style.display = "none";
    });
}

function closeFullImage() {
    const fullImg = document.getElementById('full_img');
    fullImg.style.display = "none";
}

function setURLImage() {
    let image_big = document.getElementById('image_big');
    const image_small = document.getElementById('image_small');
    const originalImage  = document.getElementById('originalImage');
    const imgInput = document.getElementById('photo_input');
    image_small.src = imgInput.value;
    originalImage.src = imgInput.value;
    image_big.src = imgInput.value;
    imgInput.value = " ";
}

function resetCover() {
    document.documentElement.style.setProperty('--radial-gradient-overlay-1', "none" );
    document.documentElement.style.setProperty('--radial-gradient-overlay-2', "none" );
    document.documentElement.style.setProperty('--linear-gradient-overlay-1', "none" );
    document.documentElement.style.setProperty('--linear-gradient-overlay-2', "none" );
    document.documentElement.style.setProperty('--border-styles', "none" );
}

function resetComposition() {
    document.documentElement.style.setProperty('--mix-blend-mode', 'none');
}

function resetAll() {

    for (let i = 0; i < sliders.length; i++) {
        let unit = sliders[i].dataset.sizing || "";
        sliders[i].value = sliders[i].defaultValue;
        document.documentElement.style.setProperty(`--${sliders[i].name}`, sliders[i].defaultValue + unit );
        sliders[i].nextElementSibling.innerHTML = sliders[i].defaultValue + unit;
        switchInscription();
        resetCover();
    }

    document.documentElement.style.setProperty('--clip-path', "none");
}

function showBasic()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'none';
    border_style.style.display = 'none';
    border.style.display = 'none';
    fonts.style.display = 'none';
    text.style.display = 'none';
    blend.style.display = 'none';
    grad.style.display = 'none';
    basic.style.display = 'block';
    trans.style.display = 'none';
}

function showTransform()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'none';
    border_style.style.display = 'none';
    border.style.display = 'none';
    fonts.style.display = 'none';
    text.style.display = 'none';
    blend.style.display = 'none';
    grad.style.display = 'none';
    trans.style.display = 'block';
    basic.style.display = 'none';
}

function showGradient()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'none';
    border_style.style.display = 'none';
    border.style.display = 'none';
    fonts.style.display = 'none';
    text.style.display = 'none';
    blend.style.display = 'none';
    grad.style.display = 'block';
    trans.style.display = 'none';
    basic.style.display = 'none';
}

function showBlend()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'none';
    border_style.style.display = 'none';
    border.style.display = 'none';
    fonts.style.display = 'none';
    text.style.display = 'none';
    blend.style.display = 'block';
    grad.style.display = 'none';
    trans.style.display = 'none';
    basic.style.display = 'none';
}

function showText()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'none';
    border_style.style.display = 'none';
    border.style.display = 'none';
    fonts.style.display = 'block';
    text.style.display = 'block';
    blend.style.display = 'none';
    grad.style.display = 'none';
    trans.style.display = 'none';
    basic.style.display = 'none';
}

function showBorder()
{
    const basic = document.getElementById('basic');
    const trans = document.getElementById('transform');
    const grad = document.getElementById('gradient');
    const blend = document.getElementById('blend');
    const text = document.getElementById('text');
    const fonts = document.getElementById('fonts');
    const border = document.getElementById('border');
    const border_style = document.getElementById('brdr_size');
    const border_styles = document.getElementById('brdr_styles');
    border_styles.style.display = 'block';
    border_style.style.display = 'block';
    border.style.display = 'block';
    fonts.style.display = 'none';
    text.style.display = 'none';
    blend.style.display = 'none';
    grad.style.display = 'none';
    trans.style.display = 'none';
    basic.style.display = 'none';
}

function pointBtn(id)
{
    let btn = document.getElementById(id);
    btn.style.backgroundColor = 'black';
    btn.style.color = 'white';
}

function unpointBtn(id)
{
    let btn = document.getElementById(id);
    btn.style.backgroundColor = 'white';
    btn.style.color = 'black';
}

function seeOriginal()
{
    let org = document.getElementById('originalImage');
    org.style.zIndex = '9999';
}

function unseeOriginal()
{
    let org = document.getElementById('originalImage');
    org.style.zIndex = '0';
}

function pointRedBtn(id)
{
    let btn = document.getElementById(id);
    btn.style.backgroundColor = 'indianred';
    btn.style.color = 'white';
}

function unpointRedBtn(id)
{
    let btn = document.getElementById(id);
    btn.style.backgroundColor = 'white';
    btn.style.color = 'indianred';
}

function pointClose() {
    const close = document.getElementById('close_full_img');
    close.style.color = 'black';
}

function unpointClose() {
    const close = document.getElementById('close_full_img');
    close.style.color = 'indianred';
}