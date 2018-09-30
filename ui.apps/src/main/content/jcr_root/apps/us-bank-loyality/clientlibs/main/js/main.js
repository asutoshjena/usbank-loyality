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
    function s() {
        $(".header").affix({offset: {top: 30}})
    }

    $(".header").on("affix.bs.affix", function () {
        $(".header-sup").css("margin-bottom", "70px")
    }), $(".header").on("affix-top.bs.affix", function () {
        $(".header-sup").css("margin-bottom", "0")
    }), $(window).resize(function () {
        $(window).width() >= 992 ? $(".header").hasClass("affix") || $(".header").hasClass("affix-top") || s() : ($(".header").removeClass("affix affix-top"), $(window).off(".affix"))
    }), $(window).width() >= 992 && s(), $(".redemption-carousel").slick({
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
        }, {
            breakpoint: 480,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2,
                dots: !0,
                arrows: !0,
                prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-left-white.png"></button>',
                nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-right-white.png"></button>'
            }
        }]
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
        }, {
            breakpoint: 480,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2,
                dots: !0,
                arrows: !0,
                prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-left-white.png"></button>',
                nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-right-white.png"></button>'
            }
        }]
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
        autoplay: !0,
        autoplaySpeed: 3e3,
        prevArrow: '<button type="button" class="slick-prev"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-left-white.png"></button>',
        nextArrow: '<button type="button" class="slick-next"><img src="/etc/clientlibs/us-bank-loyality/img/icon-chevron-right-white.png"></button>',
        responsive: [{breakpoint: 1023, settings: {dots: !0, arrows: !0, asNavFor: null}}, {
            breakpoint: 767,
            settings: {dots: !0, arrows: !0, asNavFor: null}
        }]
    }), $(".redeem-slider-nav-thumbnails").slick({
        slidesToShow: 3,
        asNavFor: ".redeem-slider",
        dots: !1,
        speed: 500,
        autoplay: !1,
        infinite: !0,
        focusOnSelect: !0,
        responsive: [{breakpoint: 767, settings: {asNavFor: null}}]
    }), $(".redeem-slider-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".redeem-slider-nav-thumbnails .slick-slide").eq(0).addClass("slick-active"), $(".redeem-slider").on("beforeChange", function (s, e, t, i) {
        var o = i;
        $(".redeem-slider-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".redeem-slider-nav-thumbnails .slick-slide[data-slick-index=" + o + "]").addClass("slick-active")
    }), $(".slick-btnPlay").clickToggle(function () {
        $(".redeem-slider").slick("slickPause"), $(this).addClass("slick-pause-btn")
    }, function () {
        $(".redeem-slider").slick("slickPlay"), $(this).removeClass("slick-pause-btn")
    }), $(".product-detail-carousal").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        speed: 500,
        arrows: !1,
        fade: !1,
        asNavFor: ".product-detail-carousal-nav-thumbnails",
        autoplay: !1,
        autoplaySpeed: 3e3
    }), $(".product-detail-carousal-nav-thumbnails").slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        asNavFor: ".product-detail-carousal",
        dots: !1,
        focusOnSelect: !0,
        infinite: !1,
        arrows: !1
    }), $(".product-detail-carousal-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".product-detail-carousal-nav-thumbnails .slick-slide").eq(0).addClass("slick-active"), $(".product-detail-carousal").on("beforeChange", function (s, e, t, i) {
        var o = i;
        $(".product-detail-carousal-nav-thumbnails .slick-slide").removeClass("slick-active"), $(".product-detail-carousal-nav-thumbnails .slick-slide").eq(o).addClass("slick-active")
    })
});