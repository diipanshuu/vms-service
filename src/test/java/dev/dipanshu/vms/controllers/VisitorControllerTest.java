package dev.dipanshu.vms.controllers;

import dev.dipanshu.vms.dtos.CreateVisitorDto;
import dev.dipanshu.vms.models.Visitor;
import dev.dipanshu.vms.services.VisitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VisitorControllerTest {

    @Mock
    private VisitorService visitorService;

    @InjectMocks
    private VisitorController visitorController;

    private Visitor visitor;
    private CreateVisitorDto createVisitorDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        visitor = new Visitor();
        visitor.setId(1L);
        visitor.setFirstName("John");
        visitor.setLastName("Doe");

        createVisitorDto = new CreateVisitorDto();
        createVisitorDto.setFirstName("John");
        createVisitorDto.setLastName("Doe");
    }

    @Test
    void testGetAllVisitors() {
        List<Visitor> visitors = Arrays.asList(visitor);
        when(visitorService.getAllVisitors()).thenReturn(visitors);

        List<Visitor> result = visitorController.getAllVisitors();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(visitorService, times(1)).getAllVisitors();
    }

    @Test
    void testGetVisitorById() {
        when(visitorService.getVisitorById(anyLong())).thenReturn(Optional.of(visitor));

        ResponseEntity<Visitor> response = visitorController.getVisitorById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
        verify(visitorService, times(1)).getVisitorById(1L);
    }

    @Test
    void testGetVisitorById_NotFound() {
        when(visitorService.getVisitorById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Visitor> response = visitorController.getVisitorById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(visitorService, times(1)).getVisitorById(1L);
    }

    @Test
    void testCreateVisitor() {
        CreateVisitorDto createVisitorDto = new CreateVisitorDto();
        Visitor visitor = new Visitor();
        when(visitorService.saveVisitor(createVisitorDto)).thenReturn(visitor);

        Visitor result = visitorController.createVisitor(createVisitorDto);

        assertEquals(visitor, result);
        verify(visitorService, times(1)).saveVisitor(createVisitorDto);
    }

    @Test
    void testUpdateVisitor_Found() {
        CreateVisitorDto createVisitorDto = new CreateVisitorDto();
        Visitor visitor = new Visitor();
        when(visitorService.getVisitorById(1L)).thenReturn(Optional.of(visitor));
        when(visitorService.saveVisitorById(createVisitorDto, 1L)).thenReturn(visitor);

        ResponseEntity<Visitor> response = visitorController.updateVisitor(1L, createVisitorDto);

        assertEquals(ResponseEntity.ok(visitor), response);
        verify(visitorService, times(1)).getVisitorById(1L);
        verify(visitorService, times(1)).saveVisitorById(createVisitorDto, 1L);
    }

    @Test
    void testUpdateVisitor_NotFound() {
        CreateVisitorDto createVisitorDto = new CreateVisitorDto();
        when(visitorService.getVisitorById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Visitor> response = visitorController.updateVisitor(1L, createVisitorDto);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(visitorService, times(1)).getVisitorById(1L);
        verify(visitorService, never()).saveVisitorById(any(), anyLong());
    }

    @Test
    void testDeleteVisitor_Found() {
        Visitor visitor = new Visitor();
        when(visitorService.getVisitorById(1L)).thenReturn(Optional.of(visitor));

        ResponseEntity<Void> response = visitorController.deleteVisitor(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(visitorService, times(1)).getVisitorById(1L);
        verify(visitorService, times(1)).deleteVisitor(1L);
    }

    @Test
    void testDeleteVisitor_NotFound() {
        when(visitorService.getVisitorById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = visitorController.deleteVisitor(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(visitorService, times(1)).getVisitorById(1L);
        verify(visitorService, never()).deleteVisitor(anyLong());
    }
}