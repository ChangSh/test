

(function($) {
	$.fn.validationEngineLanguage = function() {};
	$.validationEngineLanguage = {
		newLang: function() {
			$.validationEngineLanguage.allRules = 	{"required":{    			// Add your regex rules here, you can take telephone as an example
						"regex":"none",
						"alertText":"* 必填项",
						"alertTextCheckboxMultiple":"* 必须选择一个项目",
						"alertTextCheckboxe":"* 必须勾选"},
					"length":{
						"regex":"none",
						"alertText":"* 在 ",
						"alertText2":" 与 ",
						"alertText3": " 个字符之间"},
					"maxCheckbox":{
						"regex":"none",
						"alertText":"* 超过允许"},	
					"minCheckbox":{
						"regex":"none",
						"alertText":"* 必须选择 ",
						"alertText2":" 项目"},	
					"confirm":{
						"regex":"none",
						"alertText":"* 输入不一致"},		
					"telephone":{
						"regex":"/^[0-9\-\(\)\ ]+$/",
						"alertText":"* 不可用的电话号码格式"},	
					"email":{
						"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
						"alertText":"* 不可用的电子邮件格式"},	
					"date":{
                         "regex":"/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                         "alertText":"* 日期格式不正确, 必须符合 YYYY-MM-DD 格式"},
					"onlyNumber":{
						"regex":"/^[0-9\ ]+$/",
						"alertText":"* 仅允许数字"},	
					"noSpecialCaracters":{
						"regex":"/^[0-9a-zA-Z]+$/",
						"alertText":"* 仅允许字母、数字"},	
					"ajaxUser":{
						"file":"validateUser.php",
						"extraData":"name=eric",
						"alertTextOk":"* This user is available",	
						"alertTextLoad":"* Loading, please wait",
						"alertText":"* This user is already taken"},	
					"ajaxName":{
						"file":"validateUser.php",
						"alertText":"* This name is already taken",
						"alertTextOk":"* This name is available",	
						"alertTextLoad":"* Loading, please wait"},		
					"onlyLetter":{
						"regex":"/^[a-zA-Z\ \']+$/",
						"alertText":"* 仅字母"}
					}	
		}
	}
})(jQuery);

$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});