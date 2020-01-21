package com.deploy.demo.models;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import miscelanious.Constant;

@Entity
@ApiModel(description = "Books from the OpenLibrary & WBooks data base")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
  @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ")
  private Long id;

  @Column
  @ApiModelProperty(notes = "i.e horror, comedy, drama, etc...")
  private String genre;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Name of the book's writer")
  private String author;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's cover picture")
  private String image;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's title")
  private String title;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's subtitle")
  private String subtitle;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's publishing house name")
  private String publisher;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's release year")
  private String year;

  @Column(nullable = false)
  @NotNull
  @ApiModelProperty(notes = "Book's number of pages")
  private Integer pages;

  @Column(nullable = false, unique = true)
  @NotNull
  @ApiModelProperty(notes = "Book's unique isbn identifier")
  private String isbn;

  public Book() {

  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    checkArgument(image != null && !image.isEmpty(), Constant.NOT_NULL_MESSAGE, "image");
    this.image = image;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    checkArgument(author != null && !author.isEmpty(), Constant.NOT_NULL_MESSAGE, "author");
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    checkArgument(title != null && !title.isEmpty(), Constant.NOT_NULL_MESSAGE, "title");
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    checkArgument(subtitle != null && !subtitle.isEmpty(), Constant.NOT_NULL_MESSAGE, "subtitle");
    this.subtitle = subtitle;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    checkArgument(publisher != null && !publisher.isEmpty(), Constant.NOT_NULL_MESSAGE,
        "publisher");
    this.publisher = publisher;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    checkArgument(year != null && !year.isEmpty(), Constant.NOT_NULL_MESSAGE, "year");
    this.year = year;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    checkArgument(pages != null && pages > 0, Constant.INVALID, "pages");
    this.pages = pages;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    checkArgument(isbn != null && isNumeric(isbn), Constant.INVALID, "isbn");
    this.isbn = isbn;
  }

  public Long getId() {
    return id;
  }
}
