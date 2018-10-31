package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.DealerService;

/**
 * DeallerController
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired 
	private DealerService dealerService;
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	@GetMapping(value = "/{dealerId}")
	private DealerModel viewDealer(@PathVariable("dealerId") long dealerId, Model model) {
	return dealerService.getDealerDetailById(dealerId).get();	
	}

	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "Success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable (value = "id") long id,
			@RequestParam("alamat") String alamat,
			@RequestParam("telp") String telp) {
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(id).get();
		if(dealer.equals(null)) {
			return "Couldn't find your dealer";
		}
		dealer.setAlamat(alamat);
		dealer.setNoTelp(telp);
		dealerService.updateDealer(id, dealer);
		return "update success";
	}
	
	@GetMapping()
	private List<DealerModel> viewAllDealer(Model model){
		return dealerService.viewAllDealer();
	}
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	//terakhir ditambahkan
	@GetMapping(value="/status/{dealerId}")
	private String getStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
		return detail;
	}
/**	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return"home";
	}
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
		private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return"addDealer";
	}
	 
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return"add";
	}
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String viewDealer(String dealerId, Model model) {
		DealerModel temp = dealerService.getDealerDetailById(Long.parseLong(dealerId)).get();
		List<CarModel> archieve =temp.getListCar();
		temp.setListCar(archieve);
		model.addAttribute("dealer", temp);
		model.addAttribute("title", "Dealer " + dealerId + " information");
		return "view-dealer";
	}
	
	@RequestMapping(value="/dealer/viewall", method = RequestMethod.GET)
	private String viewDealer(Model model) {
		List<DealerModel> temp = dealerService.getAllDealer();
		model.addAttribute("dealer", temp);
		model.addAttribute("title", "All Dealer");
		return "viewall";
	}
	@RequestMapping(value="/dealer/delete/{id}", method=RequestMethod.GET)
	private String deleteDealer(@PathVariable(value = "id") Long dealerId, Model model) {
		if(dealerService.getDealerDetailById(dealerId).isPresent()) {
			DealerModel temp = dealerService.getDealerDetailById(dealerId).get();
			dealerService.deleteDealer(temp);
			return "deleteDealer";
		}
		model.addAttribute("title", "Delete Dealer");
		return "404";
	
	}
	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "id") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		model.addAttribute("dealer",dealer);
		model.addAttribute("title", "Update Dealer");
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable (value = "id") long id, @ModelAttribute Optional<DealerModel> dealer, Model model) {
		if(dealer.isPresent()) {
			dealerService.updateDealer(id, dealer);
			return "update";
		}
		model.addAttribute("title", "Update Dealer");
		return "404";
	}
	
	public static Comparator<CarModel> comparePrice = new Comparator<CarModel>() {
		  public int compare(CarModel o1, CarModel o2) {
		   Long price1 =o1.getPrice();
		   Long price2 = o2.getPrice();
		   
		   return price1.compareTo(price2);
		  }
	};
**/
	
}
