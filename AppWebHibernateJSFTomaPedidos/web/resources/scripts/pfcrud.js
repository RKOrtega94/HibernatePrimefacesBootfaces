function handleSubmit(xhr, status, args, dialog) {
    var jqDialog = jQuery('#' + dialog.id);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        dialog.hide();
    }
}
function fixPFMDialogs() {
    jQuery("body > div[data-role='page']").each(function (i) {
        var pageId = jQuery(this).attr("id");
        jQuery("body > div[id*='" + pageId + "'][class*='ui-popup']").appendTo("#" + pageId);
    });
}

$.blockUI.defaults.css.width = '20%';
$.blockUI.defaults.css.left = '40%';
$.blockUI.defaults.css.height = '5em';
$.blockUI.defaults.css.borderRadius = '10px';
$.blockUI.defaults.overlayCSS.opacity = 0.5;
$.blockUI.defaults.message = '<div style="padding-top:1em"><b:spinner name="circle-o-notch" size="2x" /> Espere... </div>';