document.addEventListener("DOMContentLoaded", async () => {
  await loadHeaderFooter();
  attachMenuEvents();
});

async function loadHeaderFooter() {

  try {
    const response = await fetch("index.html");
    if (!response.ok) throw new Error("Failed to fetch Home.html");

    const text = await response.text();
    const tempDiv = document.createElement("div");
    tempDiv.innerHTML = text;

	attachMenuEvents();
	
    document.getElementById("header-container").innerHTML = tempDiv.querySelector("header")?.outerHTML || "";
    document.getElementById("footer-container").innerHTML = tempDiv.querySelector("footer")?.outerHTML || "";
  } catch (error) {
    console.error("Error loading header/footer:", error);
  }
}

function attachMenuEvents() {
  const menuToggle = document.querySelector(".menu-toggle");
  const closeBtn = document.querySelector("#close_btn");
  const menu = document.querySelector(".menu");

  if (menuToggle && closeBtn && menu) {
    menuToggle.addEventListener("click", () => menu.classList.toggle("active"));
    closeBtn.addEventListener("click", () => menu.classList.toggle("active"));
  }
}


//Form/Signup
const forms = document.querySelector(".forms");
const pwShowHide = document.querySelectorAll(".eye-icon");
const links = document.querySelectorAll(".link");

pwShowHide.forEach(eyeIcon => {
  eyeIcon.addEventListener("click", () => {
    let pwFields = eyeIcon.parentElement.parentElement.querySelectorAll(".password");
    pwFields.forEach(password => {
      if(password.type === "password"){
        password.type = "text";
        eyeIcon.classList.replace("fa-eye-slash", "fa-eye");
        return;
      }
      password.type = "password";
      eyeIcon.classList.replace("fa-eye", "fa-eye-slash");
    })
  })
})
links.forEach(link => {
  link.addEventListener("click", e => {
    e.preventDefault(); //preventing form submit
    forms.classList.toggle("show-signup");
    })
})


//Menus
const menuToggle = document.querySelector('.menu-toggle');
const closeBtn = document.querySelector('#close_btn');
const menu = document.querySelector('.menu');

menuToggle.addEventListener('click', () => {
  menu.classList.toggle('active');
});

closeBtn.addEventListener('click', () => {
  menu.classList.toggle('active');
});



//Access the images
let slideImages = document.querySelectorAll('img');
//Access the next and prev buttons
let next = document.querySelector('.next');
let prev = document.querySelector('.prev');
//Access the indicators
let dots = document.querySelectorAll('.dot');

var counter = 0;

//code for next button
next.addEventListener('click',slideNext);
function slideNext()
{
  slideImages[counter].style.animation = 'next1 0.5s ease-in forwards';
  if(counter >= slideImages.length-1)
  {
    counter = 0;
  }
  else
  {
      counter++;
  }
  slideImages[counter].style.animation = 'next2 0.5s ease-in forwards';
  indicators();
}

//code for prev button
prev.addEventListener('click',slidePrev);
function slidePrev()
{
  slideImages[counter].style.animation = 'prev1 0.5s ease-in forwards';
  if(counter == 0)
  {
    counter = slideImages.length-1;
  }
  else
  {
      counter--;
  }
  slideImages[counter].style.animation = 'prev2 0.5s ease-in forwards';
  indicators();
}

//Auto sliding
function autosliding(){
  deleteInterval = setInterval(timer, 3000);
  function timer(){
    slideNext();
    indicators();
  }
}
autosliding();

//stop auto sliding when mouse is over
const container = document.querySelector('.slide-container');
container.addEventListener('mouseover',function(){
  clearInterval(deleteInterval);
});

//Resume sliding when mouse is out
container.addEventListener('mouseout', autosliding);

//Add and remove action class from the indicators
function indicators(){
  for(i=0; i<dots.length; i++)
  {
      dots[i].className = dots[i].className.replace(' active','');
  }
  dots[counter].className += ' active';
}

//Add click event to the indicator
function switchImage(currentImage)
{
  currentImage.classList.add('active');
  var imageId = currentImage.getAttribute('attr');
  if(imageId > counter)
  {
    slideImages[counter].style.animation = 'next1 0.5s ease-in forwards';
    counter = imageId;
    slideImages[counter].style.animation = 'next2 0.5s ease-in forwards';
  }
  else if(imageId == counter)
  {
    return;
  }
  else
  {
    slideImages[counter].style.animation = 'prev1 0.5s ease-in forwards';
    counter = imageId;
    slideImages[counter].style.animation = 'prev2 0.5s ease-in forwards';
  }
  indicators();
}







 


