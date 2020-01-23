package com.waes.diff.service.impl;

import com.waes.diff.service.DiffCheckerService;
import com.waes.diff.web.dto.DiffOffsetInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for comparing diff sides
 */
@Service
public class DiffCheckerServiceImpl implements DiffCheckerService {

    /**
     * @param left
     * @param right
     * @return
     */
    @Override
    public List<DiffOffsetInfo> compareSides(byte[] left, byte[] right) {
        boolean offSetStart = false;
        int offSetStartIndex = 0;
        List<DiffOffsetInfo> diffInformationList = new ArrayList<>();
        for (int i = 0; i < left.length; i++) {
            if ((left[i] ^ right[i]) != 0) {
                if (offSetStart == false) {
                    offSetStart = true;
                    offSetStartIndex = i;
                }
            } else if (offSetStart == true) {
                    offSetStart = false;
                    DiffOffsetInfo diffInfo = new DiffOffsetInfo();
                    diffInfo.setOffSet(offSetStartIndex);
                    diffInfo.setDiffLength(i - offSetStartIndex);
                    diffInformationList.add(diffInfo);
            }
        }
        if (offSetStart == true) {
            DiffOffsetInfo diffInfo = new DiffOffsetInfo();
            diffInfo.setOffSet(offSetStartIndex);
            diffInfo.setDiffLength(left.length - offSetStartIndex);
            diffInformationList.add(diffInfo);
        }
        return diffInformationList;
    }
}
