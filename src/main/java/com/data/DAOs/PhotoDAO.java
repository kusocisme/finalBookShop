package com.data.DAOs;

import java.util.List;

import com.data.DbUtil;
import com.model.Photo;
import com.model.Product;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PhotoDAO {
    public Photo addPhotoForProduct(Photo photo, int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            List<Photo> photos = product.getPhotos();
            if (photos.size() <= 0) {
                photo.setMain(true);
            }
            if (photo.isMain() == true) {
                for (Photo producPhoto : photos) {
                    producPhoto.setMain(false);
                }
                product.setPictureUrl(photo.getUrl());
            }

            photo.setProduct(product);
            session.save(photo);
            session.update(product);

            transaction.commit();
            return photo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Photo getPhotoById(int photoId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Photo photo = session.get(Photo.class, photoId);

            transaction.commit();
            return photo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Photo getMainProductPhoto(int productId) {
        Photo mainPhoto = null;
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            List<Photo> photos = product.getPhotos();
            for (Photo photo : photos) {
                if (photo.isMain() == true) {
                    mainPhoto = photo;
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
        return mainPhoto;
    }

    public List<Photo> getProductPhotos(int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            List<Photo> photos = product.getPhotos();
            for (Photo photo : photos) {
                photo.getId();
            }

            transaction.commit();
            return photos;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Product setMainPhotoProduct(int photoId, int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            List<Photo> photos = product.getPhotos();
            for (Photo producPhoto : photos) {
                if (producPhoto.getId() == photoId) {
                    producPhoto.setMain(true);
                    product.setPictureUrl(producPhoto.getUrl());
                } else {
                    producPhoto.setMain(false);
                }
            }

            transaction.commit();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean deletePhoto(int photoId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Photo photo = session.get(Photo.class, photoId);
            session.delete(photo);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
