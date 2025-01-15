package dev.dipanshu.vms.controllers;

import dev.dipanshu.vms.dtos.CreateVisitorDto;
import dev.dipanshu.vms.models.Visitor;
import dev.dipanshu.vms.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing visitors.
 */
@RestController
@RequestMapping("/api/v1/visitors")
@CrossOrigin(origins = "*")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    /**
     * Get all visitors.
     *
     * @return List of all visitors.
     */
    @GetMapping
    public List<Visitor> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

    /**
     * Get a visitor by ID.
     *
     * @param id The ID of the visitor.
     * @return The visitor if found, otherwise a 404 response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable Long id) {
        return visitorService.getVisitorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new visitor.
     *
     * @param createVisitorDto The DTO containing visitor details.
     * @return The created visitor.
     */
    @PostMapping
    public Visitor createVisitor(@RequestBody CreateVisitorDto createVisitorDto) {
        return visitorService.saveVisitor(createVisitorDto);
    }

    /**
     * Update an existing visitor.
     *
     * @param id The ID of the visitor to update.
     * @param createVisitorDto The DTO containing updated visitor details.
     * @return The updated visitor if found, otherwise a 404 response.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(@PathVariable Long id, @RequestBody CreateVisitorDto createVisitorDto) {
        return visitorService.getVisitorById(id)
                .map(visitor -> ResponseEntity.ok(visitorService.saveVisitorById(createVisitorDto, id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete a visitor by ID.
     *
     * @param id The ID of the visitor to delete.
     * @return A 204 response if the visitor was deleted, otherwise a 404 response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        Optional<Visitor> visitor = visitorService.getVisitorById(id);
        if (visitor.isPresent()) {
            visitorService.deleteVisitor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
