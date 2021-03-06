package com.deploy.demo.controllers;

import com.deploy.demo.models.Book;
import com.deploy.demo.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import miscelanious.Constant;
import miscelanious.exceptions.IdMismatchException;
import miscelanious.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Operations pertaining to books")
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @ApiOperation(value = "Returns the list of all books", response = List.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = Constant.OK_MESSAGE),
      @ApiResponse(code = 401, message = Constant.NOT_AUTHORIZED_MESSAGE),
      @ApiResponse(code = 403, message = Constant.FORBIDDEN_MESSAGE)
  })
  @GetMapping
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @ApiOperation(value = "Given an id, the corresponding book is returned", response = Book.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = Constant.OK_MESSAGE),
      @ApiResponse(code = 404, message = Constant.NOT_FOUND_MESSAGE),
      @ApiResponse(code = 401, message = Constant.NOT_AUTHORIZED_MESSAGE),
      @ApiResponse(code = 403, message = Constant.FORBIDDEN_MESSAGE)
  })
  @GetMapping("/{id}")
  public Book findById(
      @ApiParam(value = "id of the book to be found", required = true) @PathVariable Long id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Constant.BOOK_NOT_FOUND));
  }

  @ApiOperation(value = "Creates a Book record in the database", response = Book.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = Constant.CREATED_MESSAGE),
      @ApiResponse(code = 401, message = Constant.NOT_AUTHORIZED_MESSAGE),
      @ApiResponse(code = 403, message = Constant.FORBIDDEN_MESSAGE)
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book create(
      @ApiParam(value = "Book object store in database table", required = true) @RequestBody Book book) {
    return bookRepository.save(book);
  }

  @ApiOperation(value = "Deletes a book record given it's id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = Constant.OK_MESSAGE),
      @ApiResponse(code = 404, message = Constant.NOT_FOUND_MESSAGE),
      @ApiResponse(code = 401, message = Constant.NOT_AUTHORIZED_MESSAGE),
      @ApiResponse(code = 403, message = Constant.FORBIDDEN_MESSAGE)
  })
  @DeleteMapping("/{id}")
  public void delete(
      @ApiParam(value = "Book id of the book to be deleted from the database", required = true) @PathVariable Long id) {
    bookRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.BOOK_NOT_FOUND));
    bookRepository.deleteById(id);
  }

  @ApiOperation(value = "Updates a book record given it's id", response = Book.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = Constant.OK_MESSAGE),
      @ApiResponse(code = 404, message = Constant.NOT_FOUND_MESSAGE),
      @ApiResponse(code = 401, message = Constant.NOT_AUTHORIZED_MESSAGE),
      @ApiResponse(code = 403, message = Constant.FORBIDDEN_MESSAGE)
  })
  @PutMapping("/{id}")
  public Book updateBook(
      @ApiParam(value = "Updated book object", required = true) @RequestBody Book book,
      @ApiParam(value = "Id of the book to be updated") @PathVariable Long id) {
    if (!book.getId().equals(id)) {
      throw new IdMismatchException(Constant.BOOK_ID_MISMATCH);
    }
    bookRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.BOOK_NOT_FOUND));
    return bookRepository.save(book);
  }
}
