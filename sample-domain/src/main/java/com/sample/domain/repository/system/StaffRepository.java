package com.sample.domain.repository.system;

import static com.sample.domain.util.DomaUtils.createSelectOptions;
import static java.util.stream.Collectors.toList;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.domain.dao.system.StaffDao;
import com.sample.domain.dao.system.StaffRoleDao;
import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.dto.system.StaffRole;
import com.sample.domain.exception.NoDataFoundException;
import com.sample.domain.service.BaseRepository;

import lombok.val;

/**
 * 担当者リポジトリ
 */
@Repository
public class StaffRepository extends BaseRepository {

    @Autowired
    StaffDao staffDao;

    @Autowired
    StaffRoleDao staffRoleDao;

    /**
     * 担当者を一括取得します。
     *
     * @param where
     * @param pageable
     * @return
     */
    public Page<Staff> findAll(Staff where, Pageable pageable) {
        // ページングを指定する
        val options = createSelectOptions(pageable).count();
        val data = staffDao.selectAll(where, options, toList());
        return pageFactory.create(data, pageable, options.getCount());
    }

    /**
     * 担当者を取得します。
     *
     * @param where
     * @return
     */
    public Optional<Staff> findOne(Staff where) {
        return staffDao.select(where);
    }

    /**
     * 担当者の権限一覧を取得します。
     *
     * @param id
     * @return
     */
    public List<StaffRole> findAllRole(Long id) { return staffRoleDao.selectByStaffId(id, toList()); }

    /**
     * 担当者を取得します。
     *
     * @return
     */
    public Staff findById(final Long id) {
        return staffDao.selectById(id).orElseThrow(() -> new NoDataFoundException("staff_id=" + id + " のデータが見つかりません。"));
    }

    /**
     * 担当者を追加します。
     *
     * @param inputStaff
     * @return
     */
    public Staff create(final Staff inputStaff) {
        // 1件登録
        staffDao.insert(inputStaff);

        // 役割権限紐付けを登録する
        val staffRole = new StaffRole();
        staffRole.setStaffId(inputStaff.getId());
        staffRole.setRoleKey("admin");
        staffRoleDao.insert(staffRole);

        return inputStaff;
    }

    /**
     * 担当者を更新します。
     *
     * @param inputStaff
     * @return
     */
    public Staff update(final Staff inputStaff) {
        // 1件更新
        int updated = staffDao.update(inputStaff);

        if (updated < 1) {
            throw new NoDataFoundException("staff_id=" + inputStaff.getId() + " のデータが見つかりません。");
        }

        return inputStaff;
    }

    /**
     * 担当者を論理削除します。
     *
     * @return
     */
    public Staff delete(final Long id) {
        val staff = staffDao.selectById(id)
                .orElseThrow(() -> new NoDataFoundException("staff_id=" + id + " のデータが見つかりません。"));

        int updated = staffDao.delete(staff);

        if (updated < 1) {
            throw new NoDataFoundException("staff_id=" + id + " は更新できませんでした。");
        }

        return staff;
    }
}
