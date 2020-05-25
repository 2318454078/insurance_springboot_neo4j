new WOW().init();
/***********************************/
/*          Button Navbar          */
/***********************************/
$('.nav-btn').click(function(event){
  $('.nav-btn').toggleClass('active open-btn');
  $('.nav').toggleClass('open'); 
  $('#dim').toggleClass('dim'); 
  event.stopPropagation();
});
/***********************************/
/*Add Dark Overlay if menu is open */
/***********************************/
$("html").click(function() {
  if ($('.nav').hasClass("open")) {
    $('.nav').removeClass('open');
    $('.nav-btn').toggleClass('active open-btn');
    $('#dim').toggleClass('dim'); 
  }
}); 
// Add Class To Nav/Header With Dark Background When User Scroll Down
$(window).scroll(function() {    
  var scroll = $(window).scrollTop();
  if (scroll >= 500) {
      $("nav").addClass("darker-nav");
  }else {
    $("nav").removeClass("darker-nav");
  }
}); 
/***********************************/
/*         Portfolio Filter        */
/***********************************/
$('#filter a').click(function() {
  if($(this).attr('rel')) {
      $('.imgs img').hide().filter('[class="' + $(this).attr('rel') + '"]').show();
  } else {
      $('.imgs img').show();
  }
  return false;
});
/***********************************/
/*     Number Count Animations     */
/***********************************/
var table = document.getElementById('body');
if (table) {
  var a = 0;
  $(window).scroll(function () {
    // USE STRICT
    "use strict";
    var oTop = $('.stats-box').offset().top - window.innerHeight;
    if (a == 0 && $(window).scrollTop() > oTop) {
      $('.counter-value').each(function () {
        var $this = $(this),
          countTo = $this.attr('data-count');
        $({
          countNum: $this.text()
        }).animate({
          countNum: countTo
        },
          {
            duration: 2000,
            easing: 'swing',
            step: function () {
              $this.text(Math.floor(this.countNum));
            },
            complete: function () {
              $this.text(this.countNum);
              //alert('finished');
            }
          });
      });
      a = 1;
    }
  });
}

