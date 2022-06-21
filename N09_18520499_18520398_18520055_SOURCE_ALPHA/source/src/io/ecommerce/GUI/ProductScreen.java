package io.ecommerce.GUI;

import io.ecommerce.BUS.ProductBUS;
import io.ecommerce.DTO.Employee;
import io.ecommerce.DTO.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ProductScreen extends JFrame {
    private JPanel productPanel;
    private JTextField productNameTextField;
    private JTextArea productDesTextField;
    private JTextField productManuDateTextField;
    private JTextField productOriginTextField;
    private JTextField productPriceTextField;
    private JLabel productNameLabel;
    private JLabel productDesLabel;
    private JLabel productManuDateLabel;
    private JLabel productOriginLabel;
    private JLabel productPriceLabel;
    private JTextField productQuantityTextField;
    private JLabel quantityLabel;
    private JTextField insuranceDurTextField;
    private JLabel insuranceDurLabel;
    private JTextField discountTextField;
    private JLabel discountLabel;
    private JButton clearValuesButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable productsTable;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton infoButton;
    private JTextField productIdTextField;
    private JLabel productIdLabel;
    private JScrollPane productScrollPane;

    private ProductBUS _productBUS = new ProductBUS();
    private Employee _employee = null;
    private ArrayList<Product> _products = new ArrayList<>();

    private void _populateTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) productsTable.getModel();
        defaultTableModel.setRowCount(0);
        Object[] rowData = new Object[9];
        for (int i = 0; i < _products.size(); ++i) {
            rowData[0] = _products.get(i).getProductId();
            rowData[1] = _products.get(i).getName();
            rowData[2] = _products.get(i).getDescription();
            rowData[3] = _products.get(i).getOrigin();
            rowData[4] = _products.get(i).getManufactureDate();
            rowData[5] = _products.get(i).getQuantity();
            rowData[6] = _products.get(i).getPrice();
            rowData[7] = _products.get(i).getInsuranceDuration();
            rowData[8] = _products.get(i).getDiscountPercentage();
            defaultTableModel.addRow(rowData);
        }
    }

    private void _clearFields() {
        productIdTextField.setEnabled(true);

        productIdTextField.setText("");
        productNameTextField.setText("");
        productDesTextField.setText("");
        productOriginTextField.setText("");
        productManuDateTextField.setText("");
        productQuantityTextField.setText("");
        productPriceTextField.setText("");
        insuranceDurTextField.setText("");
        discountTextField.setText("");
    }

    private boolean _isProductInfoValid() {
        if (productIdTextField.getText().isEmpty()              ||
                productNameTextField.getText().isEmpty()        ||
                productDesTextField.getText().isEmpty()         ||
                productOriginTextField.getText().isEmpty()      ||
                productManuDateTextField.getText().isEmpty()    ||
                productQuantityTextField.getText().isEmpty()    ||
                productPriceTextField.getText().isEmpty()       ||
                insuranceDurTextField.getText().isEmpty()       ||
                discountTextField.getText().isEmpty()
        )
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Trường dữ liệu không được để trống.",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            LocalDate date = LocalDate.parse(productManuDateTextField.getText().trim());
        }
        catch (DateTimeParseException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Ngày sản xuất sai định dạnh, mặc định: YYYY-MM-DD",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            long quantity = Long.parseLong(productQuantityTextField.getText().trim());
            if (quantity < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Số lượng không thể là số âm",
                        "!!!",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Số lượng sai kiểu dữ liệu",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            double price = Double.parseDouble(productPriceTextField.getText().trim());
            if (price < 0.0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Giá thành không thể là số âm",
                        "!!!",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Giá thành sai kiểu dữ liệu",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            long insuranceDuration = Long.parseLong(insuranceDurTextField.getText().trim());
            if (insuranceDuration < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Thời hạn bảo hành không thể là số âm",
                        "!!!",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Thời hạn bảo hành sai kiểu dữ liệu",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            double discountPercentage = Double.parseDouble(discountTextField.getText().trim());
            if (discountPercentage > 1.0) {
                JOptionPane.showMessageDialog(
                        this,
                        "% giảm giá không được quá 100",
                        "!!!",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "% giảm giá sai kiểu dữ liệu",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        return true;
    }

    public ProductScreen(Employee employee) {
        setTitle("Sản phẩm");
        setContentPane(productPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        pack();

        _employee = employee;
        _products = _productBUS.getAllProducts();

        productsTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Mã sản phẩm",
                        "Tên",
                        "Nội dung",
                        "Xuất xứ",
                        "Ngày sản xuất",
                        "Số lượng",
                        "Giá thành (VNĐ)",
                        "Thời hạn bảo hành (tháng)",
                        "% giảm giá" })
                               {
                                    @Override
                                    public boolean isCellEditable(int row, int column) {
                                        return false;
                                    }
                               }
        );

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = productsTable.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }

                productIdTextField.setEnabled(false);
                productIdTextField.setText(productsTable.getValueAt(selectedRow, 0).toString());
                productNameTextField.setText(productsTable.getValueAt(selectedRow, 1).toString());
                productDesTextField.setText(productsTable.getValueAt(selectedRow, 2).toString());
                productOriginTextField.setText(productsTable.getValueAt(selectedRow, 3).toString());
                productManuDateTextField.setText(productsTable.getValueAt(selectedRow, 4).toString());
                productQuantityTextField.setText(productsTable.getValueAt(selectedRow, 5).toString());
                productPriceTextField.setText(productsTable.getValueAt(selectedRow, 6).toString());
                insuranceDurTextField.setText(productsTable.getValueAt(selectedRow, 7).toString());
                discountTextField.setText(productsTable.getValueAt(selectedRow, 8).toString());
            }
        });

        _populateTable();

        clearValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _clearFields();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!_isProductInfoValid()) {
                    return;
                }

                Product product = new Product();
                product.setProductId(productIdTextField.getText().trim());
                product.setName(productNameTextField.getText().trim());
                product.setDescription(productDesTextField.getText().trim());
                product.setOrigin(productOriginTextField.getText().trim());
                product.setManufactureDate(LocalDate.parse(productManuDateTextField.getText().trim()));
                product.setQuantity(Long.parseLong(productQuantityTextField.getText().trim()));
                product.setPrice(Double.parseDouble(productPriceTextField.getText().trim()));
                product.setInsuranceDuration(Long.parseLong(insuranceDurTextField.getText().trim()));
                product.setDiscountPercentage(Double.parseDouble(discountTextField.getText().trim()));

                if (_productBUS.addProduct(product)) {
                    JOptionPane.showMessageDialog(
                            ProductScreen.this,
                            "Sản phẩm thêm thành công",
                            "!!!",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    Thread thread = new Thread(() -> {
                        _products = _productBUS.getAllProducts();
                        _populateTable();
                    });
                    thread.start();

                    _clearFields();
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!_isProductInfoValid()) {
                    return;
                }

                Product product = new Product();
                product.setProductId(productIdTextField.getText().trim());
                product.setName(productNameTextField.getText().trim());
                product.setDescription(productDesTextField.getText().trim());
                product.setOrigin(productOriginTextField.getText().trim());
                product.setManufactureDate(LocalDate.parse(productManuDateTextField.getText().trim()));
                product.setQuantity(Long.parseLong(productQuantityTextField.getText().trim()));
                product.setPrice(Double.parseDouble(productPriceTextField.getText().trim()));
                product.setInsuranceDuration(Long.parseLong(insuranceDurTextField.getText().trim()));
                product.setDiscountPercentage(Double.parseDouble(discountTextField.getText().trim()));

                if (_productBUS.updateProductById(product)) {
                    JOptionPane.showMessageDialog(
                            ProductScreen.this,
                            "Sản phẩm chỉnh sửa thành công",
                            "!!!",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    Thread thread = new Thread(() -> {
                        _products = _productBUS.getAllProducts();
                        _populateTable();
                    });
                    thread.start();

                    _clearFields();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!_isProductInfoValid()) {
                    return;
                }

                if (_productBUS.deleteProductById(productIdTextField.getText().trim())) {
                    JOptionPane.showMessageDialog(
                            ProductScreen.this,
                            "Sản phẩm xóa thành công",
                            "!!!",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    Thread thread = new Thread(() -> {
                        _products = _productBUS.getAllProducts();
                        _populateTable();
                    });
                    thread.start();

                    _clearFields();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(() -> {
                    Product product = _productBUS.getProductByIdOrName(searchTextField.getText().trim());

                    if (searchTextField.getText().isEmpty() || product == null) {
                        _products = _productBUS.getAllProducts();
                        _populateTable();
                    }
                    else {
                        _products.clear();
                        _products.add(product);
                        _populateTable();
                    }
                });
                thread.start();
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoScreen infoScreen = new InfoScreen(ProductScreen.this, _employee);
            }
        });

        setVisible(true);
    }
}