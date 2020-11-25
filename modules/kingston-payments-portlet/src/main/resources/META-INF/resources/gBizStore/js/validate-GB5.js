	var g_ErrMsg;
	var g_ControlName;
	var g_ElementValue;
	var sFieldToValidate;
	var sFieldToValidateWith;
	var sOperation;
	var sFieldToValidateValue;
	var sFieldToValidateWithValue;
	var sExpression;
	var iControlArray;
	var g_FixStr;

	function ValidateForm(form) {
		try{
			HideServerValidationBlock();
		}
		catch (everything){
		}
		Validate(form);
		if (g_ErrMsg != '') {			
			var sWholeErrorMessage;
			sWholeErrorMessage = '<div class="ErrorHeader">';
			sWholeErrorMessage +=	'<img border="0" align="middle" alt="error" src="' + sgBizImagesPath + '/yield.gif" />';
			sWholeErrorMessage +=	'Please correct the following errors:';
			sWholeErrorMessage +=	'<ul>' + g_ErrMsg + '</ul>';
			sWholeErrorMessage += '</div>';
			
			if (document.getElementById) {
				document.getElementById("ErrorText").innerHTML = sWholeErrorMessage;
			}
			else {
				document.all.ErrorText.innerHTML = sWholeErrorMessage;
			}
			var url = window.location.toString();
			
			if (url.indexOf("#") == -1){
                 window.location.href = "#TopOfPage";
            }
            else{
                 window.scrollTo(0,0);
            }
			return false;
		}
		else {
		   if (useLoadingPopup){
		        jQuery.blockUI({'message': loadingPopupHTML}); 
		   } 
			return true;
		}
	}

	function Validate(form) {
		var bAllowBlank;
		var bPassedRequired;
		var sStructure;
		var sAttribute;
		var strCurrentElementType = new String();

		g_ErrMsg = '';	
		for (var cntl=0;cntl<form.elements.length;cntl++) {
			strCurrentElementType = form.elements[cntl].type;
			g_ElementValue = form.elements[cntl].value;
			g_ElementID = form.elements[cntl].id;
			if(g_ElementID != null && g_ElementID.length > 0){ 
			    g_FixStr = '<a href="javascript:document.getElementById(\''+g_ElementID+'\').focus();"><img class="jsErrorFixImage" src="' + sgBizImagesPath + '/fix.gif" alt="Fix it now"/></a>'
			}
			else{
			    g_ElementID = form.elements[cntl].name;
			    g_FixStr = '<a href="javascript:document.getElementsByName(\''+g_ElementID+'\').item(0).focus();"><img class="jsErrorFixImage" src="' + sgBizImagesPath + '/fix.gif" alt="Fix it now"/></a>'
			}
			
			g_ControlName = form.elements[cntl].getAttribute('caption');
			if (g_ControlName == null) {
				g_ControlName = form.elements[cntl].name;
			}
			bPassedRequired = true;

			sAttribute = '' + form.elements[cntl].getAttribute('required');
			sAttribute = sAttribute.toLowerCase();
			if (sAttribute == 'yes' || sAttribute == 'true') {
				bAllowBlank = false;
				if (form.elements[cntl].type == 'checkbox') {
					if (form.elements[cntl].checked == false) {
						bPassedRequired = false;
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is required ' + g_FixStr;
					}
				}
				else if (strCurrentElementType.indexOf('select',0)!=-1) {
					var intSelectedIndex;intSelectedIndex = form.elements[cntl].selectedIndex;
					g_ElementValue = form.elements[cntl].options[intSelectedIndex].value;
					if (isNotBlank(g_ElementValue) == false){
						bPassedRequired = false;
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is required ' + g_FixStr;
					}
				}
				else if (isNotBlank(g_ElementValue) == false) {
					bPassedRequired = false;
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is required ' + g_FixStr;
				}
			}
			else {
				bAllowBlank = true;
			}

			if (bPassedRequired == true) {
				sStructure = '' + form.elements[cntl].getAttribute('structure');
				sStructure = sStructure.toLowerCase();
				min = form.elements[cntl].getAttribute('min');
				max = form.elements[cntl].getAttribute('max');

				switch (sStructure) {
					case 'email': 
						if (IsEmail(g_ElementValue, bAllowBlank) == false) {
							g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid email ' + g_FixStr;
						}
						break;

					case 'number':
						var bAllowDecimal = true;
						var bAllowNegative = false;

						sAttribute = '' + form.elements[cntl].getAttribute('decimal');
						sAttribute = sAttribute.toLowerCase();
						if (sAttribute == 'no') {
							bAllowDecimal = false;
						}

						sAttribute = '' + form.elements[cntl].getAttribute('negative');
						sAttribute = sAttribute.toLowerCase();
						if (sAttribute == 'yes') {
							bAllowNegative = true;
						}

						if (IsNumber(g_ElementValue,bAllowBlank,bAllowDecimal,bAllowNegative) == false) {
							g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid number ' + g_FixStr;
						}
						else {
							IsWithinRange(g_ElementValue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank);
						}
						break;

						case 'date':
							var oDate = new Object();
							oDate.datevalue = g_ElementValue;
							sAttribute = form.elements[cntl].getAttribute('format');
							if (sAttribute == null) {
								sAttribute = 'y/m/d';
							}
							if (IsDate(oDate, bAllowBlank, sAttribute) == false) {
								switch(sAttribute.toLowerCase()) {
									case 'y/m/d':
										g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid date. Use format yyyy/mm/dd ' + g_FixStr;
										break;
										
									case 'y/d/m': 
										g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid date. Use format yyyy/dd/mm ' + g_FixStr;
										break;
										
									case 'm/d/y': 
										g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid date. Use format mm/dd/yyyy ' + g_FixStr;
										break;
										
									case 'd/m/y': 
										g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid date. Use format dd/mm/yyyy ' + g_FixStr;
										break;
								}
							}
							else {
								IsWithinDateRange(oDate.datevalue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank,sAttribute);
							}
							break;
							
						case 'telephone': 
							IsTelephone(g_ElementValue, bAllowBlank);
							break;
							
						case 'postalcode':
							if (IsPostalCode(g_ElementValue, bAllowBlank) == false) {
								g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid Post Code ' + g_FixStr;
							}
							break;

						case 'zipcode':
							if (IsZipCode(g_ElementValue, bAllowBlank) == false) {
								g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid Zip Code ' + g_FixStr;
							}
							break;

						case 'ziporpostalcode':
							if (IsZipOrPostalCode(g_ElementValue, bAllowBlank) == false) {
								g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid Zip or Postal Code ' + g_FixStr;
							}
							break;

						case 'time': 
							IsTime(g_ElementValue, bAllowBlank);
							break;

						case 'account': 
							if (IsNumber(g_ElementValue,bAllowBlank,false,false) == false) {
								g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid number ' + g_FixStr;
							}
							else {
								IsWithinTextRange(g_ElementValue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank);
							}

						case 'ccard': 
							break;

						case 'money': 
							var bAllowNegative = false;

							sAttribute = '' + form.elements[cntl].getAttribute('negative');
							sAttribute = sAttribute.toLowerCase();
							if (sAttribute == 'yes') {
								bAllowNegative = true;
							} 
							if (IsMoney(g_ElementValue,bAllowBlank,bAllowNegative) == false) {
								g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid currency amount ' + g_FixStr;
							}
							else {
								if (form.elements[cntl].getAttribute('max') == null) {
									IsWithinRange(g_ElementValue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank);
								}
								else {
									IsWithinRange(g_ElementValue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank);
								}
							}
							break;

						default: 
							IsWithinTextRange(g_ElementValue,form.elements[cntl].getAttribute('min'),form.elements[cntl].getAttribute('max'),bAllowBlank);
					}
				}
			}

		try {
			//Call any product related screen validations
			performAdditionalCheck();
		}
		catch (everything) {}
		
		if (g_ErrMsg == '') {
			var sFieldToValidate;
			var sFieldToValidateWith;
			var sOperation;
			var sCaption;

			for (var cntl=0;cntl<form.elements.length;cntl++) {
				sStructure = '' + form.elements[cntl].getAttribute('structure');
				sStructure = sStructure.toLowerCase();
				sFieldToValidate = form.elements[cntl].getAttribute('FieldToValidate');
				sFieldToValidateWith = form.elements[cntl].getAttribute('FieldToValidateWith');
				sOperation = form.elements[cntl].getAttribute('Operator');

				switch(sStructure) {
					case 'numbervalidator': 
						if (sFieldToValidate != null && sFieldToValidateWith != null && sOperation != null) {
							sFieldToValidateValue = document.all.item(sFieldToValidate).value;
							sFieldToValidateWithValue = document.all.item(sFieldToValidateWith).value;
							sValidateErrMsg = form.elements[cntl].getAttribute('errormessage');
							sCaption = document.all.item(sFieldToValidate).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidate = sCaption;
							}
							
							sCaption = document.all.item(sFieldToValidateWith).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidateWith = sCaption;
							}										
							Validator('number',sOperation,sFieldToValidateValue,sFieldToValidateWithValue,sValidateErrMsg,sFieldToValidate,sFieldToValidateWith) 
						}
						break;

					case 'datevalidator': 
						if (sFieldToValidate != null && sFieldToValidateWith != null && sOperation != null) {
							sFieldToValidateValue = document.all.item(sFieldToValidate).value;
							sFieldToValidateWithValue = document.all.item(sFieldToValidateWith).value;
							var oTempDate = new Object();
							oTempDate.datevalue = sFieldToValidateValue;
							sAttribute = document.all.item(sFieldToValidate).getAttribute('format');
							if (sAttribute == null) {
								sAttribute = 'y/m/d';
							}
							
							IsDate(oTempDate, false, sAttribute);
							sFieldToValidateValue = oTempDate.datevalue;

							oTempDate.datevalue = sFieldToValidateWithValue;
							sAttribute = document.all.item(sFieldToValidateWith).getAttribute('format');
							if (sAttribute == null) {
								sAttribute = 'y/m/d';
							}
							IsDate(oTempDate, false, sAttribute);
							sFieldToValidateWithValue = oTempDate.datevalue;
							sValidateErrMsg = form.elements[cntl].getAttribute('errormessage');

							sCaption = document.all.item(sFieldToValidate).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidate = sCaption;
							}
							sCaption = document.all.item(sFieldToValidateWith).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidateWith = sCaption;
							}
							Validator('date',sOperation,sFieldToValidateValue,sFieldToValidateWithValue,sValidateErrMsg,sFieldToValidate,sFieldToValidateWith) 
						}
						break;

					case 'requiredvalidator': 
						if (sFieldToValidate != null && sFieldToValidateWith != null) {
							sFieldToValidateValue = document.all.item(sFieldToValidate).value;
							sFieldToValidateWithValue = document.all.item(sFieldToValidateWith).value;
							sValidateErrMsg = form.elements[cntl].getAttribute('errormessage');
							sCaption = document.all.item(sFieldToValidate).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidate = sCaption;
							}
							sCaption = document.all.item(sFieldToValidateWith).getAttribute('caption');
							if (sCaption != null) {
								sFieldToValidateWith = sCaption;
							}
							RequiredValidator(sOperation,sFieldToValidateValue,sFieldToValidateWithValue,sValidateErrMsg,sFieldToValidate,sFieldToValidateWith) 
						}
						break;
						case 'requiredgroupvalidator':
						
							var sFieldsToValidate;
							var sFieldsToValidateValue;
							var sOperator;
						
							sFieldsToValidate = form.elements[cntl].getAttribute('fieldtovalidate');
							if (sFieldsToValidate != null) {
								sValidateErrMsg = form.elements[cntl].getAttribute('errormessage');
								sCaption = form.elements[cntl].getAttribute('caption');
								sOperator = form.elements[cntl].getAttribute('operator');
															
								RequiredGroupValidator(sOperator,sFieldsToValidate,sValidateErrMsg) 
							}
						break;
				}
			}
		}
		return g_ErrMsg == '';
	}

function RequiredGroupValidator(sOperation,sFieldsToValidate,sDefaultErrorMsg) {	 
	
		var arrRequiredFields = sFieldsToValidate.split(",");
		var iCounter;
		var iValueCount;
		var sValue;
		var blnFoundValue;
		var blnFoundBlank;
		var sItem;
		
		iValueCount = 0;
		for(iCounter = 0;iCounter<arrRequiredFields.length;iCounter++){
			sItem = arrRequiredFields[iCounter];
			sValue = document.all.item(sItem).value;
			if(typeof(document.all.item(sItem).name) =="undefined"){
				//this may be a radio button
				for (var i=0; i < document.all.item(sItem).length; i++)
				{
					if (document.all.item(sItem,i).checked)
					{
						blnFoundValue = true;
						iValueCount ++;
					}
				}
				if(!blnFoundValue){
					blnFoundBlank = true;
				}
			}
			else{			
				if(Trim(sValue) != ""){
					blnFoundValue = true;
					iValueCount ++;
				}
				else{
					blnFoundBlank = true;
				}
			}			
		}
		
		if(blnFoundValue && blnFoundBlank && sOperation.toLowerCase() == "and"){
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
				return false;
		}
		else if(blnFoundValue && iValueCount > 1 &&	sOperation.toLowerCase() == "xor"){
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
				return false;
		}
		else if(!blnFoundValue){
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
			return false;
		}
	}
	
	function Len(string) {
		if (string == null) {
			return (false);
		}
		return String(string).length;
	}

	function LTrim(string) {
		var i = 0;
		var j = string.length - 1;
		if (string== null) {
			return (false);
		}
		for (i = 0;i < string.length;i++) {
			if (string.substr(i, 1) != ' ' && string.substr(i, 1) != '\t') {
				break;
			}
		}
		if (i <= j) {
			return (string.substr(i, (j+1)-i));
		}
		else {
			return ('');
		}
	}

	function RTrim(string) {
		var i = 0;
		var j = string.length - 1;
		if (string== null) {
			return (false);
		}
		for (j = string.length - 1;j >= 0;j--) {
			if (string.substr(j, 1) != ' ' && string.substr(j, 1) != '\t') {
				break;
			}
		}
		if (i <= j) {
			return (string.substr(i, (j+1)-i));
		}
		else {
			return ('');
		}
	}

	function Trim(string) {
		if (string== null) {
			return (false);
		}
		return RTrim(LTrim(string));
	}

	function Mid(string,Start, Length) {
		if (string== null) {
			return (false);
		}
		if (Start > string.length) {
			return '';
		}
		if (Length == null || Length.length == 0) {
			return (false);
		}
		return string.substr((Start - 1), Length);
	}

	function InStr(String1, String2) {
		var a = 0;
		if (String1 == null || String2 == null) {
			return (false);
		}
		String1 = String1.toLowerCase();
		String2 = String2.toLowerCase();

		a = String1.indexOf(String2);
		if (a == -1) {
			return 0;
		}
		else {
			return a + 1;
		}
	}

	function Replace(Expression, Find, Replace) {
		var temp = Expression;
		var a = 0;
		for (var i = 0;i < Expression.length;i++) {
			a = temp.indexOf(Find);
			if (a == -1) {
				break;
			}
			else {
				temp = temp.substring(0, a) + Replace + temp.substring((a + Find.length));
			}
		}
		return temp;
	}

	function IsNumber(Expression,bAllowBlank, bAllowDecimal, bAllowNegative) {
		if (Expression == null || Expression.length == 0) {
			return (bAllowBlank);
		}
		
		Expression = Trim(Expression);
		Expression = Expression.toLowerCase();
		Expression = Replace(Expression, " ", "");
		Expression = Replace(Expression, ",", "");

		if (Expression.length < 1) {
			return (bAllowBlank);
		}
		if (bAllowDecimal == true) {
			if (bAllowNegative == true) {
				RefString = '0123456789.-';
			}
			else {
				RefString = '0123456789.';
			}
		}
		else {
			if (bAllowNegative == true) {
				RefString = '0123456789-';
			}
			else {
				RefString = '0123456789';
			}
		}
		for (var i = 0;i < Expression.length;i++) {
			var ch = Expression.substr(i, 1) 
			var a = RefString.indexOf(ch, 0) 
			if (a == -1) {
				return (false);
			}
		}		
		return(true);
	}

	function IsMoney(Expression, bAllowBlank, bAllowNegative) {
		Expression = Replace(Expression, " ", "");
		if (Expression == null || Expression.length == 0) {
			return (bAllowBlank);
		}
		
		var pattern = new RegExp(/(^\$?(?!0,?\d)\d{1,3}(,?\d{3})*(\.\d\d)?)$/ );
		return pattern.test(Expression);
	}

	function IsAlphanumeric(Expression) {
		Expression = Expression.toLowerCase();
		RefString = 'abcdefghijklmnopqrstuvwxyz0123456789 ';
		if (Expression.length < 1) {
			return (false);
		}
		for (var i = 0;i < Expression.length;i++) {
			var ch = Expression.substr(i, 1);
			var a = RefString.indexOf(ch, 0);
			if (a == -1) {
				return (false);
			}
		}
		return(true);
	}

	function isLetter (c) {
		return ( ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ) 
	}

	function isDigit (c) {
		return ((c >= '0') && (c <= '9')) 
	}

	function IsEmail(Expression, bAllowBlank) {
		if (Expression == null || Expression.length < 1)
			return (bAllowBlank);
		
		var supported = 0;
		if (window.RegExp) {
			var tempStr = "a";
			var tempReg = new RegExp(tempStr);
			if (tempReg.test(tempStr)) supported = 1;
		}
		if (!supported) {
			return (Expression.indexOf(".") > 2) && (Expression.indexOf("@") > 0);
		}
		var r1 = new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
		var r2 = new RegExp("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
		return (!r1.test(Expression) && r2.test(Expression));
	}

	function IsDate(dateObject, bAllowBlank, sFormat) {
		var ReturnVal = 0;
		var dtest;
		var aMMDDCCYY;
		var dateStr;
		dateStr = dateObject.datevalue;

		if (dateStr == null) {
			return bAllowBlank;
		}
		if (dateStr.length == 0) {
			return bAllowBlank;
		}
		if (sFormat == null) {
			sFormat = 'y/m/d';
		}
		switch(sFormat.toLowerCase()) {
			case 'y/m/d':
				var datePat = /^(\d{4})(\/|-|.)(\d{1,2})(\/|-|.)(\d{1,2})$/;
				var matchArray = dateStr.match(datePat);
				if (matchArray == null) {
					return false;
				}				
				year = matchArray[1];
				month = matchArray[3];
				day = matchArray[5];

				if (month < 1 || month > 12) {// check month range ;
					return false;
				}				
				if (day < 1 || day > 31) {
					return false;
				}				
				if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					return false;
				}				
				if (month == 2) {
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day>29 || (day==29 && !isleap)) {
						return false;
					}
				}				
				if (day.length < 2) {
					day = '0' + day;
				}
				if (month.length < 2) {
					month = '0' + month;
				}
				dateObject.datevalue = year + '/' + month + '/' + day;
				return true;
				break;

			case 'y/d/m': 
				var datePat = /^(\d{4})(\/|-|.)(\d{1,2})(\/|-|.)(\d{1,2})$/;
				var matchArray = dateStr.match(datePat);
				if (matchArray == null) {
					return false
				}				
				year = matchArray[1];
				day = matchArray[3];
				month = matchArray[5];

				if (month < 1 || month > 12) {
					return false
				}
				if (day < 1 || day > 31) {
					return false 
				}
				if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					return false 
				}
				if (month == 2) {
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day>29 || (day==29 && !isleap)) {
						return false
					}
				}	
				if (day.length < 2) {
					day = '0' + day;
				}
				if (month.length < 2) {
					month = '0' + month;
				}

				dateObject.datevalue = year + '/' + month + '/' + day;
				return true 
				break;

			case 'd/m/y': 
				var datePat = /^(\d{1,2})(\/|-|.)(\d{1,2})\2(\d{4})$/;
				var matchArray = dateStr.match(datePat);
				if (matchArray == null) {
					return false 
				}
				day = matchArray[1];
				month = matchArray[3];
				year = matchArray[4];
				if (month < 1 || month > 12) {
					return false
				}
				if (day < 1 || day > 31) {
					return false
				}
				if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					return false
				}
				if (month == 2) {
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day>29 || (day==29 && !isleap)) {
						return false
					}
				}
				if (day.length < 2) {
					day = '0' + day;
				}
				if (month.length < 2) {
					month = '0' + month;
				}
				dateObject.datevalue = year + '/' + month + '/' + day;
				return true;
				break;

			case 'm/d/y': 
				var datePat = /^(\d{1,2})(\/|-|.)(\d{1,2})\2(\d{4})$/;
				var matchArray = dateStr.match(datePat);
				if (matchArray == null) {
					return false
				}
				month = matchArray[1];
				day = matchArray[3];
				year = matchArray[4];
				if (month < 1 || month > 12) {
					return false
				}
				if (day < 1 || day > 31) {
					return false
				}
				if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					return false 
				}
				if (month == 2) {
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day>29 || (day==29 && !isleap)) {
						return false
					}
				}
				if (day.length < 2) {
					day = '0' + day;
				}
				if (month.length < 2) {
					month = '0' + month;
				}
				dateObject.datevalue = year + '/' + month + '/' + day;
				return true 
				break;
		}
	}

	function FormatDate(sDate,sFormat) {
		switch(sFormat.toLowerCase()) {
			case 'yyyy/m/d': 
			case 'yyyy/mm/dd': 
			case 'y/m/d': 
				return (sDate.substr(0,4) + '/' + sDate.substr(5,2) + '/' + sDate.substr(8,2));

			case 'yyyy/d/m': 
			case 'yyyy/dd/mm': 
			case 'y/d/m': 
				return (sDate.substr(0,4) + '/' + sDate.substr(8,2) + '/' + sDate.substr(5,2));

			case 'mm/dd/yyyy': 
			case 'm/d/yyyy': 
			case 'm/d/y': 
				return (sDate.substr(5,2) + '/' + sDate.substr(8,2) + '/' + sDate.substr(0,4));	 

			case 'dd/mm/yyyy':
			case 'd/m/y':
				return (sDate.substr(8,2) + '/' + sDate.substr(5,2) + '/' + sDate.substr(0,4));

			case 'dd-mmm-yyyy': 
				switch(sDate.substr(5, 2)) {
					case '01': 
						return (sDate.substr(8,2) + '-Jan-' + sDate.substr(0,4));
					case '02': 
						return (sDate.substr(8,2) + '-Feb-' + sDate.substr(0,4));
					case '03': 
						return (sDate.substr(8,2) + '-Mar-' + sDate.substr(0,4));
					case '04': 
						return (sDate.substr(8,2) + '-Apr-' + sDate.substr(0,4));
					case '05': 
						return (sDate.substr(8,2) + '-May-' + sDate.substr(0,4));
					case '06': 
						return (sDate.substr(8,2) + '-Jun-' + sDate.substr(0,4));
					case '07': 
						return (sDate.substr(8,2) + '-Jul-' + sDate.substr(0,4));
					case '08': 
						return (sDate.substr(8,2) + '-Aug-' + sDate.substr(0,4));
					case '09': 
						return (sDate.substr(8,2) + '-Sep-' + sDate.substr(0,4));
					case '10': 
						return (sDate.substr(8,2) + '-Oct-' + sDate.substr(0,4));
					case '11': 
						return (sDate.substr(8,2) + '-Nov-' + sDate.substr(0,4));
					case '12': 
						return (sDate.substr(8,2) + '-Dec-' + sDate.substr(0,4));
					}
			default: 
				return (sDate.substr(8,2) + '//' + sDate.substr(5,2) + '//' + sDate.substr(0,4));
		}
	}

	function isNotBlank(inputStr) {
		for(var i=0;i<inputStr.length;i++) {
			if (inputStr.charAt(i)!=' ') {
				return true;
			}
		}
		return false;
	}

	function IsTelephone(fieldVal, bAllowNull) {
		if (fieldVal.length == 0) {
			return bAllowNull;
		}
		
		var pocMASK = /((\(\d{3}\) ?)|(\d{3}-))?\d{3}-\d{4}/;
		if (pocMASK.test(fieldVal) != true) {
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not a valid telephone number ' + g_FixStr;
			return false;
		}
		else {
			return true;
		}
	}

	function IsWithinRange(Expression,MinValue,MaxValue,bAllowBlank) {
		var sErrMsg;
		if (Expression == null || Expression.length == 0) 
			return (bAllowBlank);
		if (IsNumber(Expression, false, true, true)) {
			if (IsNumber(MinValue,false, true, true) == false && IsNumber(MaxValue,false, true, true) == false) {
				return true;
			}
			if (IsNumber(MinValue,false, true, true) && IsNumber(MaxValue,false, true, true)) {
				if ((parseFloat(Expression) >= parseFloat(MinValue) == false) || (parseFloat(Expression) <= parseFloat(MaxValue) == false)) {
					sErrMsg = ' is not valid. The number must be between <MinValue> and <MaxValue> ' + g_FixStr;
					sErrMsg = Replace(sErrMsg, '<MinValue>', MinValue);
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MaxValue>', MaxValue);
					return false;
				}
				else {
					return true;
				}
			}
			if (IsNumber(MaxValue, false, true, true)) {
				if ((parseFloat(Expression) <= parseFloat(MaxValue)) == false) {
					sErrMsg = ' is not valid. The number must be less than or equal to <MaxValue> ' + g_FixStr;
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MaxValue>', MaxValue);
					return false;
				}
				else {
					return true;
				}
			}

			if (IsNumber(MinValue, false, true, true)) {
				if ((parseFloat(Expression) >= parseFloat(MinValue)) == false) {
					sErrMsg = ' is not valid. Must be a number greater than or equal to <MinValue> ' + g_FixStr;
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MinValue>', MinValue);
					return false;
				}
				else {
					return true;
				}
			}
		}
		else {
			return false;
		}
	}

	function IsPostalCode(field, bAllowBlank) {
		field = Replace(field,' ','');

		if (field == null) 
			return (bAllowBlank);
		if (field.length == 0) 
			return (bAllowBlank);
			
		var oRegExp = new RegExp('^[a-c,e,g,h,j-n,p,r-t,v,x,y]{1}[0-9]{1}[a-z]{1}[0-9]{1}[a-z]{1}[0-9]{1}$','gi');
		return oRegExp.test(field);
	}	

	function IsZipCode(field, bAllowBlank) {
		field = Replace(field,' ','');

		if (field == null) 
			return (bAllowBlank);
		if (field.length == 0) 
			return (bAllowBlank);
			
		var oRegExp;
		oRegExp = /^\d{5}([\-]\d{4})?$/;
		
		return oRegExp.test(field);
	}	

	function IsZipOrPostalCode(field, bAllowBlank) {
		var bReturnVal = IsPostalCode(field, bAllowBlank);
		if (!bReturnVal)
		{
			bReturnVal = IsZipCode(field, bAllowBlank); 
		}
		return bReturnVal;
	}

 	function IsTime(fieldVal, bAllowBlank) {
		if (fieldVal == null) {
			return (bAllowBlank);
		}
		fieldVal = Replace(fieldVal,' ','');

		if (fieldVal.length == 0) {
			return (bAllowBlank);
		}
		var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
		var matchArray = fieldVal.match(timePat);
		if (matchArray == null) {
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not in a valid time. Use the HH:MM or HH:MM:SS format ' + g_FixStr;
			return false;
		}
		hour = matchArray[1];
		minute = matchArray[3];
		second = matchArray[4];

		if (second=="") {
			second = null;
		}
		if (hour < 0|| hour > 23) {
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not in a valid time. Hour must be between 1 and 23. ' + g_FixStr;
			return false;
		}		
		if (minute<0 || minute > 59) {
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' Minute must be between 0 and 59. ' + g_FixStr;
			return false;
		}
		if (second != null && (second < 0 || second > 59)) {
			g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' is not in a valid time. Second must be between 0 and 59. ' + g_FixStr;
			return false;
		}
		if (second==null) {
			fieldVal += ':00';
		}		
		return true;
	}

	function IsWithinDateRange(Expression,MinValue,MaxValue,bAllowBlank,sFormat) {
		var sErrMsg;
		var oMinDate = new Object();
		var oMaxDate = new Object();
		if (Expression == null  || Expression.length == 0)
			return (bAllowBlank);
		oMinDate.datevalue = MinValue;
		oMaxDate.datevalue = MaxValue;

		if (IsDate(oMinDate) == false && IsDate(oMaxDate) == false) 
			return true;
		if (IsDate(oMinDate) && IsDate(oMaxDate)) {
			if ((Expression >= oMinDate.datevalue) == false || (Expression <= oMaxDate.datevalue) == false) {
				sErrMsg = 'must be between the following dates: <MinValue> and <MaxValue> ' + g_FixStr;
				sErrMsg = Replace(sErrMsg, '<MinValue>', FormatDate(oMinDate.datevalue,sFormat));
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MaxValue>', FormatDate(oMaxDate.datevalue,sFormat));
				return false;
			}
			else {
				return true;
			}
		}

		if (IsDate(oMaxDate)) {
			if ((Expression <= oMaxDate.datevalue) == false) {
				sErrMsg = 'must be less than or equal to the following date: <MaxValue> ' + g_FixStr;
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' +Replace(sErrMsg, '<MaxValue>', FormatDate(oMaxDate.datevalue,sFormat));
				return false;
			}
			else {
				return true;
			}
		}
		
		if (IsDate(oMinDate)){
			if ((Expression >= oMinDate.datevalue) == false) {
				sErrMsg = 'must be greater than or equal to the following date: <MinValue> ' + g_FixStr;
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' +Replace(sErrMsg, '<MinValue>', FormatDate(oMinDate.datevalue,sFormat));
				return false;
			}
			else {
				return true;	 
			}
		}
	}

	function IsWithinTextRange(Expression,MinValue,MaxValue,bAllowBlank) {
		var sErrMsg;
		if (Expression == null || Expression.length == 0)
			return (bAllowBlank);

		if (IsNumber(MinValue,false, false, false) == false && IsNumber(MaxValue,false, false, false) == false) 
			return true;
		if (IsNumber(MinValue,false, false, false) && IsNumber(MaxValue,false, false, false)) {
			if ((Expression.length >= MinValue) == false || (Expression.length <= MaxValue) == false) {
				if (MinValue == MaxValue) {
					sErrMsg = 'must be <MinValue> characters in length ' + g_FixStr;
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MinValue>', MinValue);
				}
				else {
					sErrMsg = 'must be between <MinValue> and <MaxValue> characters in length ' + g_FixStr;
					sErrMsg = Replace(sErrMsg, '<MinValue>', MinValue);
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MaxValue>', MaxValue);
				}

				return false;
			}
			else {
				return true;
			}
		}

		if (IsNumber(MaxValue, false, false, false)) {
			if ((Expression.length <= MaxValue) == false) {
				sErrMsg = 'must be less than or equal to <MaxValue> characters in length ' + g_FixStr;
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MaxValue>', MaxValue);
				return false;
			}
			else {
				return true;
			}
		}

		if (IsNumber(MinValue, false, false, false)) {
			if ((Expression.length >= MinValue) == false) {
				sErrMsg = 'must be greater than or equal to <MinValue> characters in length ' + g_FixStr;
				g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + g_ControlName + ' ' + Replace(sErrMsg, '<MinValue>', MinValue);
				return false;
			}
			else {
				return true;
			}
		}
	}

	function IsRegEx(sInputExpression, sDefinedExpression, bAllowBlank) {
		if (sInputExpression == null || sInputExpression.length == 0) 
			return (bAllowBlank);
		var oRegExp;
		oRegExp = new RegExp(sDefinedExpression,'i');
		return (oRegExp.test(sInputExpression));
	}

	function Validator(sValidateType,sOperation,sValValue,sValWithValue,sDefaultErrorMsg,sFieldName,sFieldWithName) {	 
		if (sValWithValue == null) 
			return true;			
		if (sValWithValue.length == 0) 
			return true;			
		if (sValValue == null) 
			return true;			 
		if (sValValue.length == 0) 
			return true;
		if (sValidateType.toLowerCase() == 'date') {
			//This function assumes dates are passed in the following format: yyyy/mm/dd
			sValValue = FormatDate(sValValue,'mm/dd/yyyy');
			sValWithValue = FormatDate(sValWithValue,'mm/dd/yyyy');
			var dValValue = new Date(sValValue);
			var dValWithValue = new Date(sValWithValue);
		}
		else {
			var dValValue = sValValue;
			var dValWithValue = sValWithValue;
		}

		switch(sOperation.toLowerCase()) {
			case 'gteq': 
				if (dValValue < dValWithValue) {
					if (sDefaultErrorMsg.length > 0) {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
					}
					else {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' must be greater than or equal to ' + sFieldWithName;
					}
					return false;
				}
				break;

			case 'gt': 
				if (dValValue <= dValWithValue) {
					if (sDefaultErrorMsg.length > 0) {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
					}
					else {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' must be greater than ' + sFieldWithName;
					}
					return false;
				}
				break;

			case 'lteq': 
				if (dValValue > dValWithValue) {
					if (sDefaultErrorMsg.length > 0) {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
					}
					else {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' must be less than or equal to ' + sFieldWithName;
					}
					return false;
				}
				break;

			case 'lt': 
				if (dValValue >= dValWithValue) {
					if (sDefaultErrorMsg.length > 0) {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
					}
					else {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' must be less than ' + sFieldWithName;
					}
					return false;
				}
				break;

			case 'eq': 
				if (dValValue != dValWithValue) {
					if (sDefaultErrorMsg.length > 0) {
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
					}
					else{
						g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' must be equal to ' + sFieldWithName;
					}
					return false;
				}
				break;
		}
	}

	function RequiredValidator(sOperation,sValValue,sValWithValue,sDefaultErrorMsg,sFieldName,sFieldWithName) {	 
		if (sOperation.toLowerCase() == 'or') {
			if (sValValue.length == 0 && sValWithValue.length == 0) {
				if (sDefaultErrorMsg == null) {
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + 'Either ' + sFieldName + ' or ' + sFieldWithName + ' is required';
				}
				else {
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
				}
				return false;
			}
		}
		else {
			if (sValValue.length == 0 && sValWithValue.length > 0) {
				if (sDefaultErrorMsg == null) {
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sFieldName + ' is required if a value for ' + sFieldWithName + ' is supplied';
				}
				else {
					g_ErrMsg = g_ErrMsg + '<li class="ErrorDetail">' + sDefaultErrorMsg;
				}
				return false;
			}
		}
	}
			
	function ValidateKeyStroke(Input, control) {
		var newInput='';
		for(i=0;i<Input.value.length;i++) {
			chck = Input.value.charAt(i);
			if(control.indexOf(chck,0)!= -1) {
				newInput+=chck;
			}
		}
		if (Input.value != newInput) {
			Input.value = newInput;
		}
	}
