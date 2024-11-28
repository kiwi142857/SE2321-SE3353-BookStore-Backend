package com.web.bookstore.daoimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.Tag;
import com.web.bookstore.repository.BookRepository;

@Service
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;
    private final GridFSBucket gridFSBucket;

    public BookDAOImpl(BookRepository bookRepository, GridFSBucket gridFSBucket) {
        this.bookRepository = bookRepository;
        this.gridFSBucket = gridFSBucket;
        // listAllGridFSFiles();
    }

    @Override
    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable).map(this::populateCoverContent);
    }

    public Page<Book> findByStockGreaterThanAndTitleContaining(Integer stock, String title, Pageable pageable) {
        return bookRepository.findByStockGreaterThanAndTitleContaining(stock, title, pageable).map(this::populateCoverContent);
    }

    public Page<Book> findByAuthorContaining(String author, Pageable pageable) {
        return bookRepository.findByAuthorContaining(author, pageable).map(this::populateCoverContent);
    }

    public Page<Book> findByAuthorContainingAndStockGreaterThanPageable(String author, Integer stock,
            Pageable pageable) {
        return bookRepository.findByAuthorContainingAndStockGreaterThanPageable(author, stock, pageable).map(this::populateCoverContent);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (book.getCover() != null) {
                try {
                    byte[] coverContent = getBookCover(book.getCover());
                    book.setCoverContent(coverContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookRepository.findById(id);
    }

    private Book populateCoverContent(Book book) {
        if (book.getCover() != null) {
            System.out.println("Populating cover content for book " + book.getId() + " with cover " + book.getCover());
            try {
                byte[] coverContent = getBookCover(book.getCover());
                // print if the CoverContent is null
                if (coverContent == null) {
                    System.out.println("CoverContent is null");
                }
                book.setCoverContent(coverContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    @Override
    public Page<Book> findByTag(Tag tag, Pageable pageable) {
        return bookRepository.findByTag(tag, pageable).map(this::populateCoverContent);
    }

    public Page<Book> findByTagAndStockGreaterThanPageable(Tag tag, Integer stock, Pageable pageable) {
        return bookRepository.findByTagAndStockGreaterThanPageable(tag, stock, pageable).map(this::populateCoverContent);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::populateCoverContent);
    }

    public byte[] getBookCover(String coverId) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectId objectId = new ObjectId(coverId);
        GridFSFile gridFSFile = gridFSBucket.find(new Document("_id", objectId)).first();
        if (gridFSFile == null) {
            System.out.println("No file found with the ObjectId: " + coverId);
            return null;
        }
        gridFSBucket.downloadToStream(gridFSFile.getObjectId(), outputStream);
        return outputStream.toByteArray();
    }

    public ObjectId storeBookCover(byte[] content, String filename) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        GridFSUploadOptions options = new GridFSUploadOptions().metadata(new Document("type", "image").append("content_type", "image/jpeg"));
        return gridFSBucket.uploadFromStream(filename, inputStream, options);
    }

    public Book save(Book book) {
        if (book.getCoverContent() != null) {
            ObjectId coverId = storeBookCover(book.getCoverContent(), book.getCover());
            book.setCover(coverId.toHexString()); // Store the ObjectId as a string in the cover field
        }
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Page<Book> findAllByOrderBySalesDesc(Pageable pageable) {
        return bookRepository.findAllByOrderBySalesDesc(pageable).map(this::populateCoverContent);
    }

    public Optional<Book> findByIsbnAndIdNot(String isbn, Integer id) {
        return bookRepository.findByIsbnAndIdNot(isbn, id).map(this::populateCoverContent);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).map(this::populateCoverContent);
    }

    // Temporary test function to list all files in GridFS
    public void listAllGridFSFiles() {
        System.out.println("Listing all GridFS files");
        MongoCursor<GridFSFile> cursor = gridFSBucket.find().iterator();
        while (cursor.hasNext()) {
            GridFSFile file = cursor.next();
            System.out.println("Filename: " + file.getFilename());
            System.out.println("ObjectId: " + file.getObjectId().toHexString());
            System.out.println("Upload Date: " + file.getUploadDate());
            System.out.println("Metadata: " + file.getMetadata());
            System.out.println("===================================");
        }
    }
}
