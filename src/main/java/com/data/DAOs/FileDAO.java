package com.data.DAOs;

import java.util.List;

import com.data.DbUtil;
import com.model.File;
import com.model.Item;
import com.model.Order;
import com.model.Product;
import com.model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileDAO {
    public File addFileForProduct(File file, int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            file.setProduct(product);
            session.save(file);

            transaction.commit();
            return file;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public File getFileById(int fileId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            File file = session.get(File.class, fileId);

            transaction.commit();
            return file;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserOwnThisFile(int userId, int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            List<Order> orders = user.getOrders();
            for (Order order : orders) {
                List<Item> items = order.getItems();
                for (Item item : items) {
                    if (productId == item.getProduct().getId()) {
                        transaction.commit();
                        return true;
                    }
                }
            }

            transaction.commit();
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public File getProductFile(int productId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);
            File file = product.getFile();

            transaction.commit();
            return file;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFile(int fileId) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            File file = session.get(File.class, fileId);
            session.delete(file);

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
