$(document).ready(function(){
	
	
	
	$("#submitNew").submit(function(){
		//let alert = alert("Hey! Welcome");
		let answer = confirm("Are you sure you're ready to submit?");
		if (answer === true){
			return true;
		} else{
			return false;
		}
	})
	
	
	
})