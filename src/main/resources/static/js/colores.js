document.addEventListener("DOMContentLoaded", () => {
  const animado = document.getElementById("animado");
  const boton = document.getElementById("btn");
  const url ="http://localhost:8080/clientes";
  boton.addEventListener("click", (e) => {
    
    console.log("click");
    animado.removeAttribute("transition-style");

    setTimeout(() => {
      animado.setAttribute("transition-style", "out:circle:top-right");
      setTimeout(() => {
        window.location.href= url;

      }, 1500);
    }, 50);
  });
});