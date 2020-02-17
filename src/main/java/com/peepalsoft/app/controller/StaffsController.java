package com.peepalsoft.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peepalsoft.app.component.CrudReturnService;
import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Staffs;
import com.peepalsoft.app.message.HttpResponses;
import com.peepalsoft.app.message.Messages;
import com.peepalsoft.app.repo.OfficeRepo;
import com.peepalsoft.app.repo.StaffsFamilyRepo;
import com.peepalsoft.app.repo.StaffsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("staffs")
public class StaffsController {
	
	@Autowired
	StaffsRepo staffsRepo;
	
	@Autowired
	StaffsFamilyRepo staffsFamilyRepo;

	@Autowired
	OfficeRepo officeRepo;
	
	@Autowired
	CrudReturnService<Staffs> crudReturnService;
	
	Office office;

	@ModelAttribute
	public void models(Model model) {
		model.addAttribute("offices", officeRepo.findAll());
	}

	@GetMapping(path = "/create-page")
	public ModelAndView createpage() {
		ModelAndView model = new ModelAndView("staff/create");
		model.addObject("pagetitle", "STAFFS");

		return model;
	}

	@GetMapping(path = "/view-page")
	public ModelAndView viewpage() {
		ModelAndView model = new ModelAndView("staff/view");
		model.addObject("pagetitle", "STAFFS");
		model.addObject("staffs", staffsRepo.findAll());
		return model;
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> read() {
		List<Staffs> list = staffsRepo.findAll();
		ResponseEntity<?> returntype = crudReturnService.controllerReturnForList(list);
		return returntype;
	}

	@GetMapping(path = "/{code}")
	public ResponseEntity<?> read(@PathVariable String code) {
		try {
			Staffs staffs = staffsRepo.findById(code).get();
			if (staffs != null) {
				return new ResponseEntity<Messages>(HttpResponses.fetched(staffs), HttpStatus.OK);
			}
		} catch (Exception e) {
		}
		return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Staffs staffs) {
		try {

			Staffs savedStaffs = staffsRepo.save(staffs);

			if (savedStaffs != null) {
				return new ResponseEntity<Messages>(HttpResponses.created(savedStaffs), HttpStatus.CREATED);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
	}

//	@PutMapping(path="/{id}")
//	public Staffs update(@PathVariable int id,@RequestBody Staffs staffs) {
//		staffs.setId(id);
//		Staffs updatedStaffs=staffsRepo.save(staffs);
//		return updatedStaffs;
//	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Messages> delete(@PathVariable String id) {
		boolean staff_present = staffsRepo.findById(id).isPresent();

		if (staff_present) {
			staffsRepo.deleteById(id);
			return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/image", consumes = { "multipart/form-data" })
	public ResponseEntity<?> saveImage(@RequestParam(required = true, value = "file") MultipartFile file,
			@RequestParam(required = true, value = "jsondata") String jsondata) {
		try {
			byte[] image_bytes = file.getBytes();

			Staffs staff_from_json = new ObjectMapper().readValue(jsondata, Staffs.class);
			String code = staff_from_json.getCode();

			Staffs tobesaved = staffsRepo.findById(code).get();
			tobesaved.setPic(image_bytes);

			Staffs savedstaff = staffsRepo.save(tobesaved);
			if (savedstaff != null) {
				return new ResponseEntity<Messages>(HttpResponses.created(savedstaff), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
	}

	// saving excel files to the db directly
		@PostMapping("/import")
		public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
			office = new Office();
			List<Staffs> tempStaffList = new ArrayList<Staffs>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				Staffs tempStaff = new Staffs();
						
				XSSFRow row = worksheet.getRow(i);
				
				try {
				int code = (int)row.getCell(0).getNumericCellValue();
				String code_i = String.valueOf(code);
				tempStaff.setCode(code_i);
				
				tempStaff.setFirstName(row.getCell(1).getStringCellValue());
				tempStaff.setLastName(row.getCell(2).getStringCellValue());
				tempStaff.setGender(row.getCell(3).getStringCellValue());
				
				try {
				int phone = (int)row.getCell(4).getNumericCellValue();
				String phoneNum = String.valueOf(phone);
				tempStaff.setPhoneNumber(phoneNum);
				}
				catch (Exception e) {
					System.out.println("exception in type conversion for phone number "+e);				
					}
				
				tempStaff.setPost(row.getCell(5).getStringCellValue());
				
				//setting office
				int officeId = (int) row.getCell(6).getNumericCellValue();
				
				office.setId(officeId);
				
				tempStaff.setOffice(office);
		
				tempStaffList.add(tempStaff);
				
				}
				catch (Exception e) {
					System.out.println("Error setting values"+e);
				}
				
			}
			
			List<Staffs> status=staffsRepo.saveAll(tempStaffList);
			workbook.close();
			if(status.size()>0)
				return "Data Upload Successful!";
			
			
			return "Data Upload Failed!";
		}
}
