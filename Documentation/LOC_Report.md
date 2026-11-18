# LOC Report -- Water Supply Management and Consumption Analysis System

## 1. Project Details

  -----------------------------------------------------------------------
  Item                                Details
  ----------------------------------- -----------------------------------
  Project Name                        Water Supply Management and
                                      Consumption Analysis System
                                      (WaterFlow)

  Metric                              Lines of Code (LOC)

  Total LOC                           **2,811 lines**

  KLOC                                **2.811 KLOC**

  Counting Method                     Non-empty source-code lines
  -----------------------------------------------------------------------

## 2. LOC Calculation

LOC (Lines of Code) is a software size measurement that counts the lines
of source code in a project.

For this project, a PowerShell script was used to: 1. Search the project
folders recursively. 2. Select the specified source-code file
extensions. 3. Exclude generated, dependency and configuration folders.
4. Count only non-empty lines in each selected file. 5. Add the
individual counts to calculate the total LOC.

### Calculation

``` text
Total LOC = 2,811

KLOC = LOC / 1,000
     = 2,811 / 1,000
     = 2.811 KLOC
```

## 3. PowerShell Script Used

Run this script from the project root folder:

``` powershell
$extensions = @('.java', '.js', '.jsx', '.ts', '.tsx', '.py', '.sql', '.html', '.css')

$files = Get-ChildItem -Path . -Recurse -File |
    Where-Object {
        $extensions -contains $_.Extension.ToLower() -and
        $_.FullName -notmatch '[\\/](\.git|node_modules|target|\.vscode|\.idea|dist|build|venv|\.venv|__pycache__)[\\/]'
    }

$results = foreach ($file in $files) {
    $count = (Get-Content -LiteralPath $file.FullName |
        Where-Object { $_.Trim().Length -gt 0 }).Count

    [PSCustomObject]@{
        File = $file.Name
        LOC  = $count
    }
}

$results | Format-Table -AutoSize

$total = ($results | Measure-Object -Property LOC -Sum).Sum

Write-Host "================================"
Write-Host "TOTAL PROJECT LOC: $total"
Write-Host "================================"
```

## 4. Counting Rules

-   Included file extensions: `.java`, `.js`, `.jsx`, `.ts`, `.tsx`,
    `.py`, `.sql`, `.html` and `.css`.
-   Blank or whitespace-only lines were excluded.
-   Comment lines were included because the script counts every
    non-empty line.
-   The following folders were excluded: `.git`, `node_modules`,
    `target`, `.vscode`, `.idea`, `dist`, `build`, `venv`, `.venv` and
    `__pycache__`.
-   The count includes matching source files found recursively under the
    project root.

## 5. Purpose of LOC

LOC is used to measure the approximate size of a software project based
on its source-code lines. It can support project-size discussions and
software effort planning.

**Note:** LOC measures code size. It does not directly measure software
quality, correctness, complexity or maintainability.

## 6. Final Result

  Measurement                        Result
  ------------------------ ----------------
  Total Lines of Code         **2,811 LOC**
  Thousand Lines of Code     **2.811 KLOC**
