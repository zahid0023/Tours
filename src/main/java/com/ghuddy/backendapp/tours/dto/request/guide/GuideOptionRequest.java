package com.ghuddy.backendapp.tours.dto.request.guide;

import lombok.Data;

import java.util.List;

@Data
public class GuideOptionRequest {
    private List<GuidePackageRequest> guidePackageRequestList;
}
