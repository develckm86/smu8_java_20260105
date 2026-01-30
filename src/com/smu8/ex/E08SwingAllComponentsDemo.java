package com.smu8.ex;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;

public class E08SwingAllComponentsDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E08SwingAllComponentsDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Swing 컴포넌트 모음");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 레이아웃: 좌(입력/선택) + 우(목록/표) + 하단(상태바)
        frame.setLayout(new BorderLayout(10, 10));

        JPanel left = buildLeftPanel();
        JPanel right = buildRightPanel();
        JLabel status = new JLabel("상태: 준비됨");

        // 상단 제목
        JLabel title = new JLabel("Swing 컴포넌트 모음 데모");
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        title.setFont(title.getFont().deriveFont(Font.BOLD, title.getFont().getSize() + 2));

        // 패널 배치
        frame.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        center.add(left);
        center.add(right);

        frame.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottom.add(status, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        // 상태바를 업데이트하도록 각 패널에 전달
        wireStatusBar(left, right, status);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new TitledBorder("입력 / 선택 컴포넌트"));

        // 폼 영역 (GridBagLayout)
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel nameLabel = new JLabel("이름 (JLabel)");
        JTextField nameField = new JTextField(16);
        nameField.setText("JTextField");

        JLabel memoLabel = new JLabel("메모 (JTextArea)");
        JTextArea memoArea = new JTextArea(6, 16);

        JScrollPane memoScroll = new JScrollPane(memoArea);

        JCheckBox agreeCheck = new JCheckBox("동의합니다 (JCheckBox)");

        JLabel genderLabel = new JLabel("성별 (JRadioButton)");
        JRadioButton rbMale = new JRadioButton("남");
        JRadioButton rbFemale = new JRadioButton("여");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);

        JLabel cityLabel = new JLabel("도시 (JComboBox)");
        JComboBox<String> cityCombo = new JComboBox<>(new String[]{"서울", "부산", "대구", "인천", "광주", "대전"});

        JButton applyButton = new JButton("적용 (JButton)");
        JButton clearButton = new JButton("초기화");

        // 배치
        int r = 0;

        gbc.gridx = 0; gbc.gridy = r; gbc.weightx = 0;
        form.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = r; gbc.weightx = 1;
        form.add(nameField, gbc);
        r++;

        gbc.gridx = 0; gbc.gridy = r; gbc.weightx = 0;
        form.add(memoLabel, gbc);
        gbc.gridx = 1; gbc.gridy = r; gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        form.add(memoScroll, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        r++;

        gbc.gridx = 0; gbc.gridy = r; gbc.gridwidth = 2;
        form.add(agreeCheck, gbc);
        gbc.gridwidth = 1;
        r++;

        gbc.gridx = 0; gbc.gridy = r; gbc.weightx = 0;
        form.add(genderLabel, gbc);
        gbc.gridx = 1; gbc.gridy = r; gbc.weightx = 1;
        form.add(genderPanel, gbc);
        r++;

        gbc.gridx = 0; gbc.gridy = r; gbc.weightx = 0;
        form.add(cityLabel, gbc);
        gbc.gridx = 1; gbc.gridy = r; gbc.weightx = 1;
        form.add(cityCombo, gbc);
        r++;

        // 버튼 영역
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        buttons.add(clearButton);
        buttons.add(applyButton);

        panel.add(form, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        // 컴포넌트 참조를 패널에 저장(상태바 연결용)
        panel.putClientProperty("nameField", nameField);
        panel.putClientProperty("memoArea", memoArea);
        panel.putClientProperty("agreeCheck", agreeCheck);
        panel.putClientProperty("rbMale", rbMale);
        panel.putClientProperty("rbFemale", rbFemale);
        panel.putClientProperty("cityCombo", cityCombo);
        panel.putClientProperty("applyButton", applyButton);
        panel.putClientProperty("clearButton", clearButton);

        // 초기화 동작
        clearButton.addActionListener(e -> {
            nameField.setText("");
            memoArea.setText("");
            agreeCheck.setSelected(false);
            genderGroup.clearSelection();
            cityCombo.setSelectedIndex(0);
            nameField.requestFocusInWindow();
        });

        return panel;
    }

    private static JPanel buildRightPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new TitledBorder("목록 / 표 / 기타"));

        // JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("사과");
        listModel.addElement("바나나");
        listModel.addElement("포도");
        listModel.addElement("딸기");
        JList<String> list = new JList<>(listModel);
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(new TitledBorder("JList"));

        // JTable
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "NAME", "CITY"}, 0);
        tableModel.addRow(new Object[]{1, "Kim", "서울"});
        tableModel.addRow(new Object[]{2, "Lee", "부산"});
        tableModel.addRow(new Object[]{3, "Park", "대구"});

        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(new TitledBorder("JTable"));

        // 오른쪽 상단: 리스트 + 테이블을 수직으로(BoxLayout)
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(listScroll);
        top.add(Box.createVerticalStrut(10));
        top.add(tableScroll);

        // 하단: 동적 추가 테스트(컴포넌트 추가 + revalidate/repaint)
        JPanel dynamicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        dynamicPanel.setBorder(new TitledBorder("동적 추가 테스트 (JPanel)"));

        JButton addChipButton = new JButton("라벨 추가");
        JButton removeChipButton = new JButton("라벨 제거");
        dynamicPanel.add(addChipButton);
        dynamicPanel.add(removeChipButton);

        addChipButton.addActionListener(e -> {
            JLabel chip = new JLabel("Label-" + (dynamicPanel.getComponentCount() - 1));
            chip.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            dynamicPanel.add(chip);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });

        removeChipButton.addActionListener(e -> {
            // 버튼 2개는 고정, 그 이후 라벨만 제거
            if (dynamicPanel.getComponentCount() > 2) {
                dynamicPanel.remove(dynamicPanel.getComponentCount() - 1);
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            }
        });

        panel.add(top, BorderLayout.CENTER);
        panel.add(dynamicPanel, BorderLayout.SOUTH);

        // 참조 저장(상태바 연결용)
        panel.putClientProperty("list", list);
        panel.putClientProperty("table", table);
        panel.putClientProperty("tableModel", tableModel);

        return panel;
    }

    private static void wireStatusBar(JPanel left, JPanel right, JLabel status) {
        JTextField nameField = (JTextField) left.getClientProperty("nameField");
        JTextArea memoArea = (JTextArea) left.getClientProperty("memoArea");
        JCheckBox agreeCheck = (JCheckBox) left.getClientProperty("agreeCheck");
        JRadioButton rbMale = (JRadioButton) left.getClientProperty("rbMale");
        JRadioButton rbFemale = (JRadioButton) left.getClientProperty("rbFemale");
        JComboBox<String> cityCombo = (JComboBox<String>) left.getClientProperty("cityCombo");
        JButton applyButton = (JButton) left.getClientProperty("applyButton");

        @SuppressWarnings("unchecked")
        JList<String> list = (JList<String>) right.getClientProperty("list");
        JTable table = (JTable) right.getClientProperty("table");
        DefaultTableModel tableModel = (DefaultTableModel) right.getClientProperty("tableModel");

        // 버튼 클릭 시: 입력값을 테이블에 추가
        applyButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String city = String.valueOf(cityCombo.getSelectedItem());
            String gender = rbMale.isSelected() ? "남" : (rbFemale.isSelected() ? "여" : "미선택");
            boolean agree = agreeCheck.isSelected();

            if (name.isEmpty()) {
                status.setText("상태: 이름을 입력하세요");
                nameField.requestFocusInWindow();
                return;
            }

            int nextId = tableModel.getRowCount() + 1;
            tableModel.addRow(new Object[]{nextId, name + " (" + gender + ", 동의=" + agree + ")", city});
            status.setText("상태: 테이블에 추가됨, 메모 길이=" + memoArea.getText().length());
        });

        // 체크/콤보 변경 시 상태 갱신
        agreeCheck.addItemListener(e -> {
            String v = (e.getStateChange() == ItemEvent.SELECTED) ? "체크됨" : "해제됨";
            status.setText("상태: 동의 체크박스 " + v);
        });

        cityCombo.addActionListener(e -> status.setText("상태: 도시 선택 = " + cityCombo.getSelectedItem()));

        // 리스트 선택 시 상태 갱신
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {
                status.setText("상태: 리스트 선택 = " + list.getSelectedValue());
            }
        });

        // 테이블 선택 시 상태 갱신
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                int row = table.getSelectedRow();
                Object id = table.getValueAt(row, 0);
                Object name = table.getValueAt(row, 1);
                status.setText("상태: 테이블 선택 = " + id + ", " + name);
            }
        });
    }
}