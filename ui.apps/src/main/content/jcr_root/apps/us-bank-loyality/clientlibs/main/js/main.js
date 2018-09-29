"use strict";
!function (s) {
    s.fn.clickToggle = function (e, t) {
        var i = [e, t];
        return this.data("toggleclicked", 0), this.click(function () {
            var e = s(this).data(), t = e.toggleclicked;
            s.proxy(i[t], this)(), e.toggleclicked = (t + 1) % 2
        }), this
    }
}(jQuery), $(function () {
    $(".redemption-carousel").slick({
        dots: !1,
        infinite: !1,
        speed: 300,
        centerMode: !1,
        slidesToShow: 4,
        slidesToScroll: 1,
        prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/button-arrow-left.png"></button>',
        nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/button-arrow-right.png"></button>',
        responsive: [{
            breakpoint: 1024,
            settings: {slidesToShow: 3, slidesToScroll: 3, infinite: !0, dots: !1, arrows: !1}
        }, {breakpoint: 480, settings: "unslick"}]
    }), $(".recommendation-carousel").slick({
        dots: !1,
        infinite: !1,
        speed: 300,
        centerMode: !1,
        slidesToShow: 4,
        slidesToScroll: 1,
        prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/button-arrow-left.png"></button>',
        nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/button-arrow-right.png"></button>',
        responsive: [{
            breakpoint: 1024,
            settings: {slidesToShow: 3, slidesToScroll: 3, infinite: !0, dots: !1, arrows: !1}
        }, {breakpoint: 480, settings: "unslick"}]
    }), $(".usb-banner-slider").slick({
        dots: !1,
        infinite: !1,
        speed: 300,
        slidesToShow: 5,
        slidesToScroll: 1,
        arrows: !1,
        prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-left.png"></button>',
        nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-right.png"></button>',
        responsive: [{
            breakpoint: 1200,
            settings: {slidesToShow: 3, slidesToScroll: 1, arrows: !0, dots: !0}
        }, {breakpoint: 600, settings: {slidesToShow: 2, slidesToScroll: 1, arrows: !0, dots: !0}}, {
            breakpoint: 480,
            settings: {slidesToShow: 1, slidesToScroll: 1, arrows: !0, dots: !0}
        }]
    }), $(".redeem-slider").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        speed: 500,
        arrows: !1,
        fade: !1,
        asNavFor: ".redeem-slider-nav-thumbnails",
        autoplay: !1,
        autoplaySpeed: 3e3,
        prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-left.png"></button>',
        nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-right.png"></button>',
        responsive: [{breakpoint: 1023, settings: {dots: !0, arrows: !0, asNavFor: null}}, {
            breakpoint: 767,
            settings: {dots: !0, arrows: !0, asNavFor: null}
        }]
    }), $(".redeem-slider-nav-thumbnails").slick({
        slidesToShow: 8,
        slidesToScroll: 1,
        asNavFor: ".redeem-slider",
        dots: !1,
        focusOnSelect: !0,
        responsive: [{breakpoint: 767, settings: {asNavFor: null}}]
    }), $(".redeem-slider-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".redeem-slider-nav-thumbnails .slick-slide").eq(0).addClass("slick-active"), $(".redeem-slider").on("beforeChange", function (s, e, t, i) {
        var o = i;
        $(".redeem-slider-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".redeem-slider-nav-thumbnails .slick-slide").eq(o).addClass("slick-active")
    }), $(".slick-btnPlay").clickToggle(function () {
        $(".redeem-slider").slick("slickPause")
    }, function () {
        $(".redeem-slider").slick("slickPlay")
    })
});