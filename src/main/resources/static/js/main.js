
// Animations
$(document).ready(function() {
    $(".animsition").animsition({
        inClass: 'fade-in',
        outClass: 'fade-out',
        inDuration: 800,
        outDuration: 800,
        linkElement: '.animsition-link',
        // e.g. linkElement: 'a:not([target="_blank"]):not([href^="#"])'
        loading: true,
        loadingParentElement: 'body', //animsition wrapper element
        loadingClass: 'animsition-loading',
        loadingInner: '', // e.g '<img src="loading.svg" />'
        timeout: true,
        timeoutCountdown: 1,
        onLoadEvent: true,
        browser: [ 'animation-duration', '-webkit-animation-duration','-moz-animation-duration'],
        // "browser" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
        // The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
        overlay : false,
        overlayClass : 'animsition-overlay-slide',
        overlayParentElement : 'body',
        transition: function(url){ window.location.href = url; }
    });
});
// Animations



$(document).ready(function () {


    // Vehicles Functionality

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (vehicle, status) {
                $('.myForm #idLabel').text(vehicle.id);
                $('.myForm #id').val(vehicle.id);
                $('.myForm #brand').val(vehicle.brand);
                $('.myForm #model').val(vehicle.model);
                $('.myForm #plate').val(vehicle.plate);
                $('.myForm #licenseRequired').val(vehicle.licenseRequired);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #idLabel').text('');
            $('.myForm #id').text('');
            $('.myForm #brand').val('');
            $('.myForm #model').val('');
            $('.myForm #plate').val('');
            $('.myForm #licenseRequired').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    // Vehicles Functionality


    // Driver Functionality

    $('.nBtnDriver, .table .eBtnDriver').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (driver, status) {
                console.log(driver)
                $('.myForm #idLabel').text(driver.id);
                $('.myForm #id').val(driver.id);
                $('.myForm #name').val(driver.name);
                $('.myForm #surname').val(driver.surname);
                $('.myForm #license').val(driver.license);

            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #idLabel').text('');
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #surname').val('');
            $('.myForm #license').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    // Driver Functionality



    // $('.table .eBtnTrip').on('click', function (event) {
    //     event.preventDefault();
    //     var href = $(this).attr('href');
    //     var text = $(this).text(); //return New or Edit
    //
    //     if (text === 'Edit') {
    //         $.get(href, function (trip, status) {
    //             console.log(trip)
    //             $('.myForm #idLabel').text(trip.id);
    //             $('.myForm #id').val(driver.id);
    //             $('.myForm #name').val(driver.name);
    //             $('.myForm #surname').val(driver.surname);
    //             $('.myForm #license').val(driver.license);
    //
    //         });
    //         $('.myForm #exampleModal').modal();
    //     } else {
    //         $('.myForm #idLabel').text('');
    //         $('.myForm #id').val('');
    //         $('.myForm #name').val('');
    //         $('.myForm #surname').val('');
    //         $('.myForm #license').val('');
    //         $('.myForm #exampleModal').modal();
    //     }
    // });

    // Trip Functionality


    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });

    $('#datepicker').datepicker({
        uiLibrary: 'bootstrap4'
    });




})


// Stepper

$(document).ready(function () {


    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        console.log('value not eguals')
        return arg !== value;
    }, "Value must not equal arg.");



    var form = $("#trip-form");
    form.validate({
        errorPlacement: function errorPlacement(error, element) {
            element.before(error);
        },
        rules: {
            datepicker: {
                required: true,
            },
            comboVehicle: {
                valueNotEquals: '',
            },
            comboDriver: {
                valueNotEquals: '',
            },

        },
        messages: {
            datepicker: {
                required: "Please enter a date for the trip"
            },
            comboVehicle: {
                valueNotEquals: "Please pick a vehicle"
            },
            comboDriver: {
                valueNotEquals: "Please pick a Driver"
            }
        },
        // onfocusout: function (element) {
        //     $(element).valid();
        // },
        highlight: function (element, errorClass, validClass) {
            $(element).parent().parent().find('.form-group').addClass('form-error');
            $(element).removeClass('valid');
            $(element).addClass('error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().parent().find('.form-group').removeClass('form-error');
            $(element).removeClass('error');
            $(element).addClass('valid');
        }
    });
    /*form.steps({
        headerTag: "h3",
        bodyTag: "fieldset",
        transitionEffect: "fade",
        labels: {
            previous: 'Previous',
            next: 'Next',
            finish: 'Finish',
            current: ''
        },
        titleTemplate: '<h3 class="title">#title#</h3>',
        onInit: function (event, currentIndex) {
            console.log('onInit')
            // Suppress (skip) "Warning" step if the user is old enough.
            if (currentIndex === 0) {
                form.find('.actions').addClass('test');
            }

            // Datepicker

            $('#datepicker').datepicker({
                uiLibrary: 'bootstrap4'
            });

            // Datepicker


        },
        onStepChanging: function (event, currentIndex, newIndex) {
            console.log('onStepChanging')
            console.log(form.validate())
            form.validate().settings.ignore = ":disabled,:hidden";
            return form.valid();
        },
        onFinishing: function (event, currentIndex) {
            console.log('onFinishing')
            form.validate().settings.ignore = ":disabled";
            return form.valid();
        },
        onFinished: function (event, currentIndex) {
            alert('Sumited');
        },
        onStepChanged: function (event, currentIndex, priorIndex) {
            console.log('onStepChanged')

        }
    });*/

    // jQuery.extend(jQuery.validator.messages, {
    //     required: "",
    //     remote: "",
    //     email: "",
    //     url: "",
    //     date: "",
    //     dateISO: "",
    //     number: "",
    //     digits: "",
    //     creditcard: "",
    //     equalTo: ""
    // });


})

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.your_picture_image')
                .attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

// Stepper