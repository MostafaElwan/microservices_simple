package com.elwan.microservices.customerservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elwan.microservices.customerservice.config.Configuration;
import com.elwan.microservices.customerservice.domain.Segment;
import com.elwan.microservices.customerservice.service.SegmentService;
 
@Controller
public class SegmentController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
	private Configuration config;
 
    @Autowired
    private SegmentService segmentService;
 
    @GetMapping(value = "/segments")
    public String getSegments(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<Segment> segments = segmentService.findAll(pageNumber, config.getRowsPerPage());
        
        long count = segmentService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("segments", segments);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "segment-list";
    }
 
    @GetMapping(value = "/segments/{segmentId}")
    public String getSegmentById(Model model, @PathVariable String segmentId) {
    	
        Segment segment = null;
        try {
            segment = segmentService.findById(segmentId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("segment", segment);        
        
        model.addAttribute("contextPath", config.getContextPath());
        return "segment";
    }
 
    @GetMapping(value = {"/segments/add"})
    public String showAddSegment(Model model) {
        Segment segment = new Segment();
        model.addAttribute("add", true);
        model.addAttribute("segment", segment);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "segment-edit";
    }
     
    @PostMapping(value = "/segments/add")
    public String addSegment(Model model, @ModelAttribute("segment") Segment segment) {        
        try {
            Segment newSegment = segmentService.save(segment);
            return "redirect:"+config.getContextPath()+"/segments/" + String.valueOf(newSegment.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", true);
            return "segment-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
     
    @GetMapping(value = {"/segments/{segmentId}/edit"})
    public String showEditSegment(Model model, @PathVariable String segmentId) {
        Segment segment = null;
        try {
            segment = segmentService.findById(segmentId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("segment", segment);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "segment-edit";
    }
     
    @PostMapping(value = {"/segments/{segmentId}/edit"})
    public String updateSegment(Model model, @PathVariable String segmentId, @ModelAttribute("segment") Segment segment) {
        try {
            segment.setId(segmentId);
            segmentService.update(segment);
            return "redirect:"+config.getContextPath()+"/segments/" + String.valueOf(segment.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "segment-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/segments/{segmentId}/delete"})
    public String showDeleteSegmentById(Model model, @PathVariable String segmentId) {
        Segment segment = null;
        try {
            segment = segmentService.findById(segmentId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("segment", segment);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "segment";
    }
     
    @PostMapping(value = {"/segments/{segmentId}/delete"})
    public String deleteSegmentById(Model model, @PathVariable String segmentId) {
        try {
            segmentService.deleteById(segmentId);
            return "redirect:"+config.getContextPath()+"/segments";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "segment";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
}